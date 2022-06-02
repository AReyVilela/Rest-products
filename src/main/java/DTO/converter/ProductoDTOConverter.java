package DTO.converter;

import DTO.ProductoDTO;
import com.example.products.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductoDTOConverter {

private ModelMapper modelMapper;

public ProductoDTO convertToDTO(Product producto){


    return modelMapper.map(producto, ProductoDTO.class);
}

}
