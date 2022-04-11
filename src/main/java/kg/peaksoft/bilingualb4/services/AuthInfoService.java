package kg.peaksoft.bilingualb4.services;

import kg.peaksoft.bilingualb4.dto.request.AuthInfoRequest;
import kg.peaksoft.bilingualb4.dto.response.AuthInfoResponse;

public interface AuthInfoService {

    AuthInfoResponse returnToken(AuthInfoRequest authInfoRequest);

}
