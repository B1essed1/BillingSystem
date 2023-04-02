package shakh.billingsystem.services;

import shakh.billingsystem.models.ApiResponse;

import shakh.billingsystem.models.PaymentsDto;

public interface PaymentsService {
    ApiResponse payingDebts(PaymentsDto payments);
}
