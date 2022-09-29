package shakh.billingsystem.services.implemenations;

import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsRegDto;
import shakh.billingsystem.services.DebitorsService;

import java.util.Date;

@Service
public class DebitorsServiceImpl implements DebitorsService {
    @Override
    public Debitors registerDebitors(DebitorsRegDto dto) {
        return Debitors.builder()
                .additionalDetail(dto.getAdditionalDetails())
                .createdDate(new Date())
                .debt(dto.getDebt())
                .deposit(dto.getDeposit())
                .isDeleted(false)
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .build();
    }
}
