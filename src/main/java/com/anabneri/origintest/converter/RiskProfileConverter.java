package com.anabneri.origintest.converter;

import com.anabneri.origintest.domain.InsurancePlan;
import com.anabneri.origintest.dto.RiskScoreResponseDTO;
import com.anabneri.origintest.model.UserRiskProfile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
public class RiskProfileConverter  {

    public RiskScoreResponseDTO convert(final UserRiskProfile userRiskProfile) {
        return RiskScoreResponseDTO.builder()
            .auto(mappingScorePoints(userRiskProfile.autoScore))
            .disability(mappingScorePoints(userRiskProfile.disabilityScore))
            .home(mappingScorePoints(userRiskProfile.homeScore))
            .life(mappingScorePoints(userRiskProfile.lifeScore))
            .build();
    }

    private String mappingScorePoints(Integer score) {
        if (score == Integer.MIN_VALUE)
            return InsurancePlan.INELIGIBLE.toString();
        if (score <= 0)
            return InsurancePlan.ECONOMIC.toString();
        if (score == 1 || score == 2)
            return InsurancePlan.REGULAR.toString();
        return InsurancePlan.RESPONSIBLE.toString();
    }

}
