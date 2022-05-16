package com.hyundai.controller;

import java.io.File;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.FileCopyUtils;

import com.hyundai.entity.UploadResultDTO;
import com.hyundai.service.BoardService;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

/**
 * 파일 업로드 컨트롤러
 * @author 이지은
 *
 */
@Log4j2
@RestController
public class UploadController {

	@Autowired
	private BoardService boardService;

	// 파일 저장 경로 설정
	@Value("${com.hyundai.upload.path}")
	private String uploadPath;

	/**
	 * 이지은 작성 파일을 저장할 폴더 생성
	 */
	private String createFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")); // 오늘 날짜
		String folderPath = str.replace("/", File.separator); // 날짜 구분
		File uploadPathFolder = new File(uploadPath, folderPath); // 폴더 생성

		if (uploadPathFolder.exists() == false) { // 폴더가 아직 없을 경우, 생성
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}

	/**
	 * 이지은 작성 파일 업로드
	 * @param bno:         해당 게시물 번호
	 * @param uploadFiles: 게시물에 포함된 파일 목록
	 */
	@PostMapping("/uploadAjax/{bno}")
	public ResponseEntity<List<UploadResultDTO>> uploadFiles(@PathVariable("bno") long bno,
			MultipartFile[] uploadFiles) {

		List<UploadResultDTO> resultDTOList = new ArrayList<>(); // JSON으로 보낸 객체를 저장할 리스트

		if (uploadFiles != null) {
			for (MultipartFile file : uploadFiles) { // 파일 여러 개 처리 가능
				// 이미지 파일이 맞는지 확인
				if (file.getContentType().startsWith("image") == false) {
					log.warn("This file is not image type");
					return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 이미지 파일이 아닐 경우 403 ERROR
				}

				String originalName = file.getOriginalFilename(); // 파일 이름
				String folderPath = createFolder(); // 파일을 저장할 폴더 생성
				String uuid = UUID.randomUUID().toString(); // UUID
				String saveName = uploadPath + File.separator + folderPath 
						+ File.separator + uuid + "_" + originalName; // 저장할 파일 이름																							// 이름
				Path savePath = Paths.get(saveName); // 파일 저장 경로

				// 이미지 파일 저장
				try {
					file.transferTo(savePath);
					String thumnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid
							+ "_" + originalName;
					File thumbailFile = new File(thumnailSaveName);
					Thumbnailator.createThumbnail(savePath.toFile(), thumbailFile, 100, 100); // 100 X 100 섬네일 파일 생성

					// 파일 데이터 저장
					UploadResultDTO uploadResultDTO = new UploadResultDTO();
					uploadResultDTO.setBno(bno);
					uploadResultDTO.setFname(originalName);
					uploadResultDTO.setUuid(uuid);
					uploadResultDTO.setFfolder(folderPath);

					resultDTOList.add(uploadResultDTO); // 각 파일 데이터 리스트에 추가
					boardService.insertFile(uploadResultDTO); // tbl_file 테이블에 insert

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK); // JSON 형식의 파일 데이터 리스트
	}

	/**
	 * 이지은 작성 해당 게시물에 포함된 파일 목록 조회
	 * @param bno: 게시물 번호
	 */
	@GetMapping("/files/{bno}")
	public ResponseEntity<List<UploadResultDTO>> fileList(@PathVariable("bno") long bno) {

		ResponseEntity<List<UploadResultDTO>> entry = null;
		try { // 파일 목록 조회
			entry = new ResponseEntity<List<UploadResultDTO>>(boardService.getFileList(bno), HttpStatus.OK);
			log.info(entry);

		} catch (Exception e) {
			e.printStackTrace();
			entry = new ResponseEntity<List<UploadResultDTO>>(HttpStatus.BAD_REQUEST);
		}
		return entry;
	}

	/**
	 * 이지은 작성 이미지 파일 출력
	 * @param fileName: 출력할 이미지 파일 이름
	 */
	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(String fileName) {
		
		ResponseEntity<byte[]> result = null;
		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8"); // 파일 이름 decoding
			File file = new File(uploadPath + File.separator + srcFileName); // 해당하는 파일 GET

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", Files.probeContentType(file.toPath())); // 헤더에 콘텐츠 타입 설정

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 ERROR
		}
		return result;
	}

	/**
	 * 이지은 작성 이미지 삭제
	 * @param fileName: 삭제할 이미지 파일 이름
	 */
	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName, long fno) {
		
		String srcFileName = null;
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8"); // 파일 이름 decoding

			// 원본 파일 삭제
			File file = new File(uploadPath + File.separator + srcFileName); // 원본 파일 삭제
			boolean result = file.delete();
			// 섬네일 파일 삭제
			File thumnailfile = new File(file.getParent(), "s_" + file.getName()); // 섬네일 파일 삭제
			result = thumnailfile.delete();

			boardService.deleteFile(fno); // DB에서 파일 정보 삭제
			return new ResponseEntity<>(result, HttpStatus.OK); // 파일 삭제 결과와 200 CODE

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR); // 500 ERROR
		}
	}
}