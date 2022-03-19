package com.daiancosta.brokeragenote.services.negotiation;

import com.daiancosta.brokeragenote.domain.entities.Negotiation;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileNegotiationService {
    boolean hasExcelFormat(MultipartFile file);

    List<Negotiation> mapData(InputStream is);
}
