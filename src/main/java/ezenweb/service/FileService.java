package ezenweb.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService {
    String upload="C:\\Users\\504\\Desktop\\ezen2023B_web2\\build\\resources\\main\\static\\uploadImg\\";
    // 프로필 사진 업데이트
    public String FileUpload(MultipartFile multipartFile){
        String uuid= UUID.randomUUID().toString();
        String filename =uuid+"_"+multipartFile.getOriginalFilename().replace("_","-");
        File file= new File(upload+filename);
        //2.
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);
            return null;
        }
        return filename;
    }
}