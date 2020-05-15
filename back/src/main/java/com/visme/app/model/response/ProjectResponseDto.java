package com.visme.app.model.response;


import com.visme.app.data.domain.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponseDto {

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
