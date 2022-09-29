package shakh.billingsystem.services.implemenations;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shakh.billingsystem.entities.*;
import shakh.billingsystem.models.CustomResponseDto;
import shakh.billingsystem.models.OrderItemsDto;
import shakh.billingsystem.models.ProductOrderDto;
import shakh.billingsystem.repositories.*;
import shakh.billingsystem.services.OrderService;
import shakh.billingsystem.services.ProductService;

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
    public CustomResponseDto<?> sell(ProductOrderDto orderDto)  {
        try {
            List<OrderItemsDto> orderItems = orderDto.getItemsDto();
            Debitors debitors = null;
            if (orderDto.getPaidCost()-orderDto.getTotalCost() != 0) {
                if (orderDto.getDebitorsId() != null) {
                    debitors = repository.findById(orderDto.getDebitorsId()).get();
                } else {
                    return CustomResponseDto.builder()
                            .isError(true)
                            .message("3" +
                                    "Qarzga olayotgan odam biriktirilmagan!")
                            .build();
                }
            }
            Admins admin = adminRepository.findAdminsByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();

            Orders orders = new Orders();
            orders.setDebitors(debitors);
            orders.setCreatedTime(new Date());
            orders.setPaidCost(orderDto.getPaidCost());
            orders.setTotalCost(orderDto.getTotalCost());
            orders.setAdmin(admin);
            orders = ordersRepository.save(orders);

            Payments payments = new Payments();
            payments.setPayment(orderDto.getPaidCost());
            payments.setDebitors(debitors);
            payments.setOrders(orders);
            payments.setAdmins(admin);
            paymentsRepository.save(payments);



            for (OrderItemsDto orderItem : orderItems) {
                OrderItems items = new OrderItems();
                Products products = productService.findProductById(orderItem.getProductId());
                if (products.getAmount()<orderItem.getAmount()) {
                    return CustomResponseDto.builder()
                            .isError(true)
                            .message("Maxsulot soni bazadi sondan ko'p kiritilgan")
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
                items.setAdmins(admin);
                orderItemsRepository.save(items);
            }
        } catch (Exception e){
            return CustomResponseDto.builder()
                    .isError(true)
                    .message("Nomalum hatolik yuz berdi " + e)
                    .build();
        }
        return CustomResponseDto.builder()
                .isError(false)
                .build();
    }
}
