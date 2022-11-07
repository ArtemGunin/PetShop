package ua.com.gunin.NIX11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.gunin.NIX11.dto.ProductDTO;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.enums.Color;
import ua.com.gunin.NIX11.model.enums.Manufacturer;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;
import ua.com.gunin.NIX11.service.product.ProductService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String getOne(@PathVariable String id, Model model) {
        ProductDTO productDTO = productService.findById(id);
        productDTO.setImgUrl("../../" + productDTO.getImgUrl());
        model.addAttribute("product", productDTO);
        return "product";
    }

    @GetMapping("/find-by-title")
    public String findByTitle(@RequestParam String title, Model model) {
        List<ProductDTO> list = productService.findByTitle(title);
        list.forEach(productDTO -> productDTO.setImgUrl("../../" + productDTO.getImgUrl()));
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-product-type")
    public String findByProductType(@RequestParam ProductType productType, Model model) {
        List<ProductDTO> list = productService.findByProductType(productType);
        list.forEach(productDTO -> productDTO.setImgUrl("../../" + productDTO.getImgUrl()));
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-manufacturer")
    public String findByManufacturer(@RequestParam Manufacturer manufacturer, Model model) {
        List<ProductDTO> list = productService.findByManufacturer(manufacturer);
        list.forEach(productDTO -> productDTO.setImgUrl("../../" + productDTO.getImgUrl()));
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-pet-type")
    public String  findByPetType(@RequestParam PetType petType, Model model) {
        List<ProductDTO> list = productService.findByPetType(petType);
        list.forEach(productDTO -> productDTO.setImgUrl("../../" + productDTO.getImgUrl()));
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-color")
    public String  findByColor(@RequestParam Color color, Model model) {
        List<ProductDTO> list = productService.findByColor(color);
        list.forEach(productDTO -> productDTO.setImgUrl("../../" + productDTO.getImgUrl()));
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/create")
    public ModelAndView getFormArticle(ModelAndView modelAndView) {
        final Product product = new Product();
        product.setPrice(500);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("createProduct");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView createOne(@ModelAttribute @Valid Product product, ModelAndView modelAndView) {
        productService.create(product);
        return modelAndView;
    }

    @GetMapping("/product/updateProduct/{id}")
    public String update(@PathVariable("id") String productId, Model model) {
        if (productId == null) {
            throw new IllegalArgumentException("Product is not provided");
        }
        ProductDTO productDTO = productService.findById(productId);
        if (productDTO != null) {
            model.addAttribute("product", productDTO);
            return "updateProduct";
        } else {
            throw new RuntimeException("Product Id exception.");
        }
    }

    @PostMapping("/product/updateProduct")
    public String update(ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("Product is not provided");
        }
        productService.update(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/product/basket/{id}")
    public String addBasket(@PathVariable String id, Principal principal) {
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBasket(id, principal.getName());
        return "redirect:/products";
    }

}
