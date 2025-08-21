package com.yourcompany.resourceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public ResponseStatusDTO addProduct(ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setPrice(dto.getPrice());
        return productDAO.save(p);
    }

    public ProductDTO getProductById(Long id) {
        Product p = productDAO.findById(id);
        if (p == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        return dto;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> list = productDAO.findAll();
        List<ProductDTO> out = new ArrayList<>();
        if (list != null) {
            for (Product p : list) {
                ProductDTO dto = new ProductDTO();
                dto.setName(p.getName());
                dto.setPrice(p.getPrice());
                out.add(dto);
            }
        }
        return out;
    }

    public ResponseStatusDTO updateProduct(Long id, ProductDTO dto) {
        return productDAO.update(id, dto);
    }

    public ResponseStatusDTO deleteProduct(Long id) {
        return productDAO.delete(id);
    }
}
