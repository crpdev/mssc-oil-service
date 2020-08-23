package com.crpdev.common.events;

import com.crpdev.msscoilservice.web.model.OilDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by rajapandian
 * Date: 23/08/20
 * Project: mssc-oil-service
 * Package: com.crpdev.common.events
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OilEvent implements Serializable {

    private static final long serialVersionUID = -5652890209086522749L;

    private OilDto oilDto;
}
