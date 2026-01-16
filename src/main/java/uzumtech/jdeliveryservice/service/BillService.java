package uzumtech.jdeliveryservice.service;

import uzumtech.jdeliveryservice.dto.request.BillRequest;
import uzumtech.jdeliveryservice.dto.response.BillResponse;

public interface BillService {
    BillResponse calculateParcel(BillRequest billRequest);
}
