package org.test.categorieservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdsRequest {
    private List<Long> ids;

    // Getter and Setter
}
