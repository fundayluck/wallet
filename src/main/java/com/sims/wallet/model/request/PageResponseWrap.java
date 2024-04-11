package com.sims.wallet.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponseWrap<T> {
    private List<T> records;
    private Integer offset;
    private Integer limit;
}
