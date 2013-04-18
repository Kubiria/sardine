package com.github.sardine.ant.command;

import com.github.sardine.ant.Command;

import java.io.IOException;

/**
 * A nice ant wrapper around sardine.delete().
 *
 * @author Jon Stevens
 */
public class Delete extends Command
{
	/** */
	private String url;

	/** */
	@Override
	public void execute() throws IOException
	{
		this.getTask().getSardine().delete(this.url);
	}

	/** */
	@Override
	protected void validateAttributes()
	{
		if (this.url == null)
		{
			throw new IllegalArgumentException("url cannot be null");
		}
	}

	/** */
	public void setUrl(String url)
	{
		this.url = url;
	}
}