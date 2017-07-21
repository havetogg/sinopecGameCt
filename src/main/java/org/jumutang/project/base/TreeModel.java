package org.jumutang.project.base;
/**
 * 树类型
 * @author Developer
 *
 */
public class TreeModel
{	
	private String id;
	
	private String name;
	
	private String nodeType;
	
	private String parentId;
	
	private String isParent;
	
	private String urlAction;
	
	private String checked;
	
	public TreeModel()
	{
		super();
	}

	public TreeModel(String id,String name,String nodeType,String parentId,String isParent)
	{
		super();
		this.id = id;
		this.name = name;
		this.nodeType = nodeType;
		this.parentId = parentId;
		this.isParent = isParent;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getParentId()
	{
		return parentId;
	}

	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}

	public String getIsParent()
	{
		return isParent;
	}

	public void setIsParent(String isParent)
	{
		this.isParent = isParent;
	}

	public String getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(String nodeType)
	{
		this.nodeType = nodeType;
	}

	public String getUrlAction()
	{
		return urlAction;
	}

	public void setUrlAction(String urlAction)
	{
		this.urlAction = urlAction;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
}
