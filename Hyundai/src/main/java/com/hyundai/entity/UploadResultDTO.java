package com.hyundai.entity;

import java.net.URLEncoder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UploadResultDTO {
	private long fno;
	private long bno;
	private String fname;
	private String uuid;
	private String ffolder;
	
	public String getImageURL(){
		try{
			return URLEncoder.encode(ffolder + "/" + uuid + "_" + fname,"UTF-8");
		}catch (Exception e){
			e.printStackTrace();
		}//end try
		return "fail";
   }//end get..
   
   public String getThumbnailURL(){
	   try{
		   return URLEncoder.encode(ffolder + "/"+"s_"+uuid+"_"+fname,"UTF-8");
       }catch (Exception e){
           e.printStackTrace();
       }//end try
       return "Thumb fail";
   }//end getTh.

}
