package shakh.billingsystem.services.implemenations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.models.DebitorsDto;
import shakh.billingsystem.models.DebitorsRegDto;
import shakh.billingsystem.repositories.DebitorsRepository;
import shakh.billingsystem.services.DebitorsService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebitorsServiceImpl implements DebitorsService {


    private final DebitorsRepository debitorsRepository;
    @Override
    public Debitors registerDebitors(DebitorsRegDto dto) {
        return Debitors.builder()
                .additionalDetail(dto.getAdditionalDetails())
                .createdDate(new Date())
                .isDeleted(false)
                .phoneNumber(dto.getPhoneNumber())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .build();
    }

    @Override
    public Map<String, Object> getDebitors(int page, int size) {

        Pageable pageable = PageRequest.of(page,size);
        Optional<Page<DebitorsDto>> debitors = debitorsRepository.findDebitorsByDebt(pageable);

        if (debitors.isPresent()){
            Map<String,Object> response = new HashMap<>();
            response.put("debitors",debitors.get().getContent());
            response.put("totalPages",debitors.get().getTotalPages());
            response.put("totalElements",debitors.get().getTotalElements());
            response.put("currentPage",debitors.get().getNumber());
            return response;
        }else{
            return null;
        }
    }


}
