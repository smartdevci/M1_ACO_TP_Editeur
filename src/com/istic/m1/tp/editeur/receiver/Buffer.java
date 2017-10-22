package com.istic.m1.tp.editeur.receiver;

public class Buffer {

	private StringBuffer texte_du_buffer;

	public Buffer() {
		super();
		this.texte_du_buffer =new StringBuffer("");
	}

	public StringBuffer getStringBuffer() {
		return texte_du_buffer;
	}

	public void setText(String texte_du_buffer) {
		this.texte_du_buffer = new StringBuffer(texte_du_buffer);
	}

	public void setStringBuffer(StringBuffer buffer) {
		this.texte_du_buffer = buffer;
	}



	public String getText()
	{
		return texte_du_buffer.toString();
	}



	@Override
	public String toString() {
		return "Buffer [texte_du_buffer=" + texte_du_buffer + "]";
	}



}
