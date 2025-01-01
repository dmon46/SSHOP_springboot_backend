package dmon.SSHOP_springboot_backend._repository.product.proj;

import java.time.Instant;
import java.util.List;

public interface IProductProjection {
    String getId();
    String getStatus();
    String getName();
    String getThumb();
    Float getRetailPrice();
    Instant getUpdatedAt();
    IProductMetricProjection getMetric();
    List<ISkuProjection> getSkus();

    //THE NESTED INTERFACE//
    interface IProductMetricProjection {
        Integer getStocks();
        Integer getSales();
    }

    interface ISkuProjection {
        String getStatus();
        String getSkuCode();
        String getTierName();
        Float getRetailPrice();
        IInventoryProjection getInventory();
    }

    interface IInventoryProjection {
        Integer getStocks();
        Integer getSales();
    }
}
