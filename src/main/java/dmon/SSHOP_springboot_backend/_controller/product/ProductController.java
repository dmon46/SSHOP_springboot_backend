package dmon.SSHOP_springboot_backend._controller.product;

import dmon.SSHOP_springboot_backend._repository.product.proj.IProductProjection;
import dmon.SSHOP_springboot_backend._service.product.IProductService;
import dmon.SSHOP_springboot_backend.base.PageRes;
import dmon.SSHOP_springboot_backend.dto.request.product.ProductCreateReq;
import dmon.SSHOP_springboot_backend.dto.response.product.ProductRes;
import dmon.SSHOP_springboot_backend.entity.product.Product;
import dmon.SSHOP_springboot_backend.utils.AppUtil;
import dmon.SSHOP_springboot_backend.utils.constant.HasRole;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    @RestController
    @RequestMapping("/admin/api/v1/product")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Admin {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product for admin");
        }
    }

    @RestController
    @RequestMapping("/seller/api/v1/product")
    @PreAuthorize(HasRole.SELLER)
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Seller {
        IProductService productService;

        //CREATE//
        @PostMapping("/create")
        public ResponseEntity<ProductRes> create(@RequestBody @Valid ProductCreateReq productDto) {
            return ResponseEntity
                    .ok()
                    .body(this.productService.create(productDto));
        }

        //LIST//
        @GetMapping("/list")
        public ResponseEntity<PageRes<IProductProjection>> findAll(
                @RequestParam(defaultValue = "1") int page,
                @RequestParam(defaultValue = "4") int size,
                @RequestParam(defaultValue = "createdAt") String sort,
                @RequestParam(defaultValue = "dsc") String direct //todo: use 1 RequestParam, instead of 4
        ) {
            Pageable pageable = AppUtil.pageableOf(page, size, sort, direct);
            return ResponseEntity
                    .ok()
                    .body(this.productService.findAll(pageable));
        }
    }

    @RestController
    @RequestMapping("/api/v1/product")
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public static class Buyer {
        //LIST//
        @GetMapping("/list")
        public ResponseEntity<String> findAll() {
            return ResponseEntity
                    .ok()
                    .body("The product on SSHOP");
        }
    }
}
