package com.daiancosta.brokeragenote.services;

import com.daiancosta.brokeragenote.helpers.ExcelHelper;
import com.daiancosta.brokeragenote.domain.entities.Business;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileMovementServiceImpl implements FileMovementService {
    public void save(MultipartFile file) {
        try {
            List<Business> tutorials = ExcelHelper.excelToBusiness(file.getInputStream());
            //publish file
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

}
