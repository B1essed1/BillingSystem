package shakh.billingsystem.services.implemenations;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shakh.billingsystem.entities.*;
import shakh.billingsystem.models.ApiResponse;
import shakh.billingsystem.models.OrderItemsDto;
import shakh.billingsystem.models.ProductOrderDto;
import shakh.billingsystem.repositories.*;
import shakh.billingsystem.services.OrderService;
import shakh.billingsystem.services.ProductService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final PaymentsRepository paymentsRepository;
    private final ProductService productService;
    private final DebitorsRepository repository;
    private final AdminRepository adminRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final OrdersRepository ordersRepository;


    @Transactional
    @Override
    public ApiResponse<?> sell(ProductOrderDto orderDto)  {
        try {
            List<OrderItemsDto> orderItems = orderDto.getItemsDto();
            Debitors debitors = null;
            double debt = orderDto.getTotalCost()-orderDto.getPaidCost();
            if ( debt!= 0) {
                if (orderDto.getDebitorsId() != null) {
                    debitors = repository.findById(orderDto.getDebitorsId()).get();
                    debitors.setDebt(debitors.getDebt() + debt);
                } else {
                    return ApiResponse.builder()
                            .isError(true)
                            .message("Qarzga olayotgan odam biriktirilmagan!")
                            .build();
                }
            }
            Admins admin = adminRepository.findAdminsByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();


            Payments payments = new Payments();
            payments.setPayment(orderDto.getPaidCost());
            payments.setDebitors(debitors);
            payments.setAdmins(admin);
            payments.setCreatedDate(new Date());
            paymentsRepository.save(payments);

            Orders orders = new Orders();
            orders.setDebitors(debitors);
            orders.setCreatedTime(new Date());
            orders.setPaidCost(orderDto.getPaidCost());
            orders.setTotalCost(orderDto.getTotalCost());
            orders.setAdmin(admin);
            orders.setPayments(Collections.singletonList(payments));
            orders = ordersRepository.save(orders);




            for (OrderItemsDto orderItem : orderItems) {
                OrderItems items = new OrderItems();
                Products products = productService.findProductById(orderItem.getProductId());
                if (products.getAmount()<orderItem.getAmount()) {
                    return ApiResponse.builder()
                            .isError(true)
                            .message("Maxsulot soni magazindagi maxsulot  sonidan ko'p kiritilgan")
                            .build();
                }
                products.setAmount(products.getAmount() - orderItem.getAmount());
                productService.save(products);
                items.setCreatedTime(new Date());
                items.setPriceOfSell(orderItem.getPriceOfSell());
                items.setCategory(orderItem.getCategory());
                items.setAmount(orderItem.getAmount());
                items.setPriceOfBuy(orderItem.getPriceOfBuy());
                items.setProductName(orderItem.getProductName());
                items.setOrders(orders);
                items.setBarcode(orderItem.getBarcode());
                items.setAdmins(admin);
                orderItemsRepository.save(items);
            }
        } catch (Exception e){
            return ApiResponse.builder()
                    .isError(true)
                    .message("Nomalum hatolik yuz berdi " + e)
                    .build();
        }
        return ApiResponse.builder()
                .isError(false)
                .build();
    }
}
