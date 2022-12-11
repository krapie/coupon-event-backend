package com.krapi.coupon_api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.krapi.coupon_api.dao.Coupon;
import com.krapi.coupon_api.dto.CouponStatusDto;
import io.swagger.annotations.ApiModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@ApiModel(value = "전체 쿠폰 발급 현황 조희 응답 정보", description = "페이지 방식으로 정보를 반환함")
public record CouponStatusGetAllResponseDto(
        @JsonProperty("couponList") List<CouponStatusDto> couponStatusList,
        @JsonProperty("pageable") Pageable pageable,
        @JsonProperty("isLast") Boolean isLast
) {
    public CouponStatusGetAllResponseDto(Page<Coupon> couponPage) {
        this(
                couponPage.toList().stream().map(CouponStatusDto::new).collect(Collectors.toList()),
                couponPage.getPageable(),
                couponPage.isLast()
        );
    }
}
