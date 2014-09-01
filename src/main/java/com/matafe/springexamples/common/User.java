package com.matafe.springexamples.common;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Description
 * 
 * @author Mauricio Tavares Ferraz
 */
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Date createAt;

	public User()
	{
	}

	public User(Long id, String name)
	{
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the createAt
	 */
	public Date getCreateAt()
	{
		return createAt;
	}

	/**
	 * @param createAt the createAt to set
	 */
	public void setCreateAt(Date createAt)
	{
		this.createAt = createAt;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof User))
		{
			return false;
		}
		User other = (User) obj;
		if (id == null)
		{
			if (other.id != null)
			{
				return false;
			}
		} else if (!id.equals(other.id))
		{
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=").append(id).append(", name=").append(name)
				.append(", createAt=").append(createAt)
				.append("]");
		return builder.toString();
	}

}
