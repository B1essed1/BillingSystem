package shakh.billingsystem.repositories;


import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import shakh.billingsystem.models.ProductNameDto;

@Repository
public interface ProductElRepository extends ElasticsearchRepository<ProductNameDto,Long> {
    ProductNameDto findByName(String name);
}
