package ua.com.gunin.NIX11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.com.gunin.NIX11.dto.ProductDTO;
import ua.com.gunin.NIX11.model.Product;
import ua.com.gunin.NIX11.model.enums.PetType;
import ua.com.gunin.NIX11.model.enums.ProductType;
import ua.com.gunin.NIX11.service.product.ProductService;

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

    @GetMapping("/{id}")
    public String getOne(@PathVariable String id, Model model) {
        ProductDTO list = productService.findById(id);
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-title")
    public String findByTitle(@RequestParam String title, Model model) {
        List<ProductDTO> list = productService.findByTitle(title);
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-product-type")
    public String findByProductType(@RequestParam ProductType productType, Model model) {
        List<ProductDTO> list = productService.findByProductType(productType);
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/find-by-pet-type")
    public String  findByPetType(@RequestParam PetType petType, Model model) {
        List<ProductDTO> list = productService.findByPetType(petType);
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

    @GetMapping("/updateProduct/{id}")
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

    @PostMapping("/updateProduct")
    public String update(ProductDTO productDTO) {
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("Product is not provided");
        }
        productService.update(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/basket/{id}")
    public String addBasket(@PathVariable String id, Principal principal) {
        if (principal == null) {
            return "redirect:/products";
        }
        productService.addToUserBasket(id, principal.getName());
        return "redirect:/products";
    }

}
