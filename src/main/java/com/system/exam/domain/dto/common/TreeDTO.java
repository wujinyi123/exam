package com.system.exam.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 树形数据DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TreeDTO {
    private String id;
    private String title;
    private String pid;
    private List<TreeDTO> children;
}
