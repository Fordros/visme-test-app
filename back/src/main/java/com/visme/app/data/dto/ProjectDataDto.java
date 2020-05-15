package com.visme.app.data.dto;

import com.visme.app.data.domain.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDataDto {

    /**
     * ID
     */
    private Long id;

    /**
     * Title
     */
    private String title;

    /**
     * Type
     */
    private ProjectType type;

    /**
     * Favorite state
     */
    private Boolean favorite;

    /**
     * Created time
     */
    private Long createdAt;

    /**
     * Modified time
     */
    private Long modifiedAt;
}
