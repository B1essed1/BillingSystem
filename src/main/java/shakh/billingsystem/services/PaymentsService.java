package shakh.billingsystem.services;

import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.models.PaymentsDto;

public interface PaymentsService {
    CustomResponseDto payingDebts(PaymentsDto payments);
}
