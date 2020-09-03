package com.crpdev.msscoilservice.bootstrap;

import com.crpdev.factory.oil.model.OilType;
import com.crpdev.msscoilservice.domain.Oil;
import com.crpdev.msscoilservice.repository.OilRepository;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.UUID;

//@Component
public class OilLoader implements CommandLineRunner {

    public static final String OIL_PRODUCTCODE_1 = "8005235079489";
    public static final String OIL_PRODUCTCODE_2 = "4987176014894";
    public static final String OIL_PRODUCTCODE_3 = "4987176014893";
    public static final UUID OIL_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID OIL_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID OIL_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final OilRepository oilRepository;

    public OilLoader(OilRepository oilRepository) {
        this.oilRepository = oilRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadOilData();
    }

    private void loadOilData() {

        if(oilRepository.count() == 0){
            oilRepository.save(Oil.builder()
            .oilName("Gold Winner")
            .oilType(OilType.COCUNUT.name())
            .productCode(OIL_PRODUCTCODE_1)
            .price(new BigDecimal("100"))
            .minOnHand(10)
            .quantityToGrind(100)
            .build());

            oilRepository.save(Oil.builder()
                    .oilName("Fortune")
                    .oilType(OilType.GINGELY.name())
                    .productCode(OIL_PRODUCTCODE_2)
                    .price(new BigDecimal("150"))
                    .minOnHand(5)
                    .quantityToGrind(200)
                    .build());

            oilRepository.save(Oil.builder()
                    .oilName("Thangam")
                    .oilType(OilType.GROUND_NUT.name())
                    .productCode(OIL_PRODUCTCODE_3)
                    .price(new BigDecimal("250"))
                    .minOnHand(20)
                    .quantityToGrind(5)
                    .build());
        }

    }
}
