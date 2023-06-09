package io.github.hossensyedriadh.springcrud.controller;

import io.github.hossensyedriadh.springcrud.entity.Product;
import io.github.hossensyedriadh.springcrud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ModelAndView products(Model model) {
        model.addAttribute("products", this.productService.products());

        return new ModelAndView("products/products");
    }

    @GetMapping("/{sku}")
    public ModelAndView product(@PathVariable("sku") String sku, Model model) {
        Optional<Product> productOptional = this.productService.product(sku);
        productOptional.ifPresent((product) -> model.addAttribute("product", product));

        return new ModelAndView("products/product");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/save")
    public ModelAndView saveForm(Model model) {
        model.addAttribute("product", new Product());
        return new ModelAndView("products/add-product");
    }

    @GetMapping("/update/{sku}")
    public ModelAndView updateForm(@PathVariable("sku") String sku, Model model) {
        Optional<Product> productOptional = this.productService.product(sku);
        productOptional.ifPresent(product -> model.addAttribute("product", product));

        return new ModelAndView("products/update-product");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/")
    public ModelAndView save(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("product", product);
            return new ModelAndView("products/add-product");
        }

        Product savedProduct = this.productService.save(product);
        return new ModelAndView(new RedirectView("/products/" + savedProduct.getSku()));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{sku}")
    public ModelAndView delete(@PathVariable("sku") String sku) {
        this.productService.delete(sku);
        return new ModelAndView(new RedirectView("/products/"));
    }
}
