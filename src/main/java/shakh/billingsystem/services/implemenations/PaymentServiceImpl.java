package shakh.billingsystem.services.implemenations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shakh.billingsystem.entities.Admins;
import shakh.billingsystem.entities.Debitors;
import shakh.billingsystem.entities.Orders;
import shakh.billingsystem.entities.Payments;
import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.models.PaymentsDto;
import shakh.billingsystem.repositories.AdminRepository;
import shakh.billingsystem.repositories.DebitorsRepository;
import shakh.billingsystem.repositories.OrdersRepository;
import shakh.billingsystem.repositories.PaymentsRepository;
import shakh.billingsystem.services.PaymentsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentsService {

    private final DebitorsRepository debitorsRepository;
    private final OrdersRepository ordersRepository;
    private final AdminRepository adminRepository;

    private final PaymentsRepository paymentsRepository;

    @Transactional
    @Override
    public CustomResponseDto payingDebts(PaymentsDto payments) {

        try {


            Debitors debitors = debitorsRepository.findById(payments.getDebitorId()).get();
            String user = SecurityContextHolder.getContext().getAuthentication().getName();
            Admins admin = adminRepository.findAdminsByUsername(user).get();
            debitors.setDebt(debitors.getDebt()-payments.getPayment());
            debitorsRepository.save(debitors);

            List<Orders> unpaidOrders = ordersRepository.findUnpaidOrder(payments.getDebitorId());
            Payments payment = new Payments();
            payment.setPayment(payments.getPayment());
            payment.setCreatedDate(new Date());
            payment.setDebitors(debitors);
            payment.setAdmins(admin);
            payment.setIsDeleted(false);
            paymentsRepository.save(payment);

            double totalPaymentForDebt = payments.getPayment();
            int i = 0;
            while (totalPaymentForDebt > 0 && unpaidOrders.size()> i) {
                Orders order = unpaidOrders.get(i);
                double paymentForDebt = order.getTotalCost() - order.getPaidCost();
                ArrayList payment_ = new ArrayList();
                payment_.add(payment);
                if (totalPaymentForDebt - paymentForDebt > 0) {
                    totalPaymentForDebt -= paymentForDebt;
                    order.setPaidCost(order.getPaidCost() + paymentForDebt);
                    order.setPayments(payment_);
                    ordersRepository.save(order);
                } else {
                    order.setPaidCost(order.getPaidCost() + totalPaymentForDebt);
                    totalPaymentForDebt = 0;
                    order.setPayments(payment_);
                    ordersRepository.save(order);
                }
                i++;
            }

        } catch (Exception e) {
            System.out.println("exception " + e);
        }


        return CustomResponseDto.builder()
                .message(payments.getPayment() + "miqdoridagi qarzingiz qoplandi")
                .isError(false)
                .build();
    }
}
