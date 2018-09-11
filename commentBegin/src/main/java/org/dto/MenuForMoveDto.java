package org.dto;

public class MenuForMoveDto {
	//拖拽的节点
    private Integer dropNodeId;
    
    //拖拽的目标节点
    private Integer targetNodeId;
    
    //移动的类型
    private String moveType;
    
    public static final String MOVE_TYPE_INNER="inner";
    
    public static final String MOVE_TYPE_PREV="prev";

    public static final String MOVE_TYPE_NEXT="next";

	public Integer getDropNodeId() {
		return dropNodeId;
	}

	public void setDropNodeId(Integer dropNodeId) {
		this.dropNodeId = dropNodeId;
	}

	public Integer getTargetNodeId() {
		return targetNodeId;
	}

	public void setTargetNodeId(Integer targetNodeId) {
		this.targetNodeId = targetNodeId;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
    
    

} 
