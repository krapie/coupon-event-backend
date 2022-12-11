package com.krapi.coupon_api.controller;

import com.krapi.coupon_api.dto.request.CouponIssueRequestDto;
import com.krapi.coupon_api.dto.response.CouponGetResponseDto;
import com.krapi.coupon_api.dto.response.CouponIssueResponseDto;
import com.krapi.coupon_api.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Api(tags = "쿠폰 API")
@RequiredArgsConstructor
@RequestMapping(value = "/coupons", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class CouponController {

    private final CouponService couponService;

    @ApiOperation(value = "구폰 발급")
    @PostMapping("/issue")
    public Mono<ResponseEntity<?>> issueCoupon(@RequestBody Mono<CouponIssueRequestDto> requestDto) {
        return requestDto
                .flatMap(couponIssueRequestDto ->
                        couponService.issueCoupon(couponIssueRequestDto.userId(), couponIssueRequestDto.couponCode()))
                .map(couponWalletDto ->
                        ResponseEntity.ok(new CouponIssueResponseDto(couponWalletDto)));
    }

    @ApiOperation(value = "쿠폰 ID로 쿠폰 정보 조회")
    @GetMapping("/{couponId}")
    public Mono<ResponseEntity<?>> getCoupon(
            @ApiParam(value = "쿠폰 ID", required = true, example = "8be67b7b-98f4-47dd-be64-839bb77b4534")
            @PathVariable String couponId
    ) {
        return couponService.getCoupon(couponId)
                .map(couponWalletDto -> ResponseEntity.ok(new CouponGetResponseDto(couponWalletDto)));
    }

    @ApiOperation(value = "전체 쿠폰 발급 현황 조회")
    @GetMapping("/status")
    public Mono<ResponseEntity<?>> getAllCouponStatus(
            @ApiParam(value = "페이지 인덱스", example = "0")
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,

            @ApiParam(value = "페이지 장수", example = "10")
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        return couponService.getAllCouponStatus(PageRequest.of(page, pageSize))
                .map(ResponseEntity::ok);
    }
}
