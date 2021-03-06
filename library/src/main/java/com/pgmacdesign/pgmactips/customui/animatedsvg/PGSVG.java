package com.pgmacdesign.pgmactips.customui.animatedsvg;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Simple Enum for sample and setting manually
 * Full credit of this class / code goes to Jared Rummler:
 * https://github.com/jaredrummler/PGAnimatedSvgView
 */
public class PGSVG {
	
	//region Vars
	
	@SerializedName("glyphs")
	private String[] glyphs;
	@SerializedName("colors")
	private int[] colors;
	@SerializedName("width")
	private float width;
	@SerializedName("height")
	private float height;
	
	//endregion
	
	//region Constructors
	
	/**
	 * Base constructor
	 * @param glyphs The glyph paths to set. IE: "M142.9,24.2c40.2-13.9,85.3-13.6,125.3,1.1c22.2,8.2"
	 * @param colors the colors to match the glyphs. Must be the same length as the glyphs array
	 * @param width width
	 * @param height height
	 */
	public PGSVG(@NonNull String[] glyphs, int[] colors, float width, float height) {
		this.glyphs = glyphs;
		this.colors = colors;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Base constructor. Overloaded to allow for a list
	 * @param glyphs The glyph paths to set. IE: "M142.9,24.2c40.2-13.9,85.3-13.6,125.3,1.1c22.2,8.2"
	 * @param colors the colors to match the glyphs. Must be the same length as the glyphs array
	 * @param width width
	 * @param height height
	 */
	public PGSVG(@NonNull List<String> glyphs, int[] colors, float width, float height) {
		this.glyphs = glyphs.toArray(new String[glyphs.size()]);
		this.colors = colors;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Overloaded to set a single fill color
	 * @param glyphs
	 * @param width
	 * @param height
	 */
	public PGSVG(@NonNull String[] glyphs, int color, float width, float height) {
		this.glyphs = glyphs;
		this.colors = new int[]{color};
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Overloaded to automatically set the fill color to black
	 * @param glyphs
	 * @param width
	 * @param height
	 */
	public PGSVG(@NonNull String[] glyphs, float width, float height) {
		this.glyphs = glyphs;
		this.colors = new int[]{0xFF000000};
		this.width = width;
		this.height = height;
	}
	
	//endregion
	
	//region Setters and Getters
	
	public String[] getGlyphs() {
		return glyphs;
	}
	
	public void setGlyphs(String[] glyphs) {
		this.glyphs = glyphs;
	}
	
	public int[] getColors() {
		return colors;
	}
	
	public void setColors(int[] colors) {
		this.colors = colors;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	
	//endregion
	
	//region Pre-Build Objects
	
	/**
	 * Sample Google icon.
	 * NOTE! I DO NOT OWN ANY RIGHTS TO GOOGLE'S COLORS NOR THEIR LOGO,
	 * THIS IS FOR DEMONSTRATION PURPOSES ONLY AND THE DEVELOPER OF THIS PROJECT RETAINS NO LIABILITY FOR ITS USE!
	 * @return {@link this}
	 */
	public static PGSVG getSGVGoogle(){
		return new PGSVG(
				new String[]{
						"M142.9,24.2c40.2-13.9,85.3-13.6,125.3,1.1c22.2,8.2,42.5,21,59.9,37.1c-5.8,6.3-12.1,12.2-18.1,18.3 c-11.4,11.4-22.8,22.8-34.2,34.2c-11.3-10.8-25.1-19-40.1-23.6c-17.6-5.3-36.6-6.1-54.6-2.2c-21,4.5-40.5,15.5-55.6,30.9 c-12.2,12.3-21.4,27.5-27,43.9c-20.3-15.8-40.6-31.5-61-47.3C59,73.6,97.6,39.7,142.9,24.2z",
						"M21.4,163.2c3.3-16.2,8.7-32,16.2-46.8c20.3,15.8,40.6,31.5,61,47.3c-8,23.3-8,49.2,0,72.4 c-20.3,15.8-40.6,31.6-60.9,47.3C18.9,246.7,13.2,203.6,21.4,163.2z",
						"M203.7,165.1c58.3,0,116.7,0,175,0c5.8,32.7,4.5,66.8-4.7,98.8c-8.5,29.3-24.6,56.5-47.1,77.2 c-19.7-15.3-39.4-30.6-59.1-45.9c19.5-13.1,33.3-34.3,37.2-57.5c-33.8,0-67.6,0-101.4,0C203.7,213.5,203.7,189.3,203.7,165.1z",
						"M37.5,283.5c20.3-15.7,40.6-31.5,60.9-47.3c7.8,22.9,22.8,43.2,42.6,57.1c12.4,8.7,26.6,14.9,41.4,17.9 c14.6,3,29.7,2.6,44.4,0.1c14.6-2.6,28.7-7.9,41-16.2c19.7,15.3,39.4,30.6,59.1,45.9c-21.3,19.7-48,33.1-76.2,39.6 c-31.2,7.1-64.2,7.3-95.2-1c-24.6-6.5-47.7-18.2-67.6-34.1C67,328.9,49.6,307.5,37.5,283.5z"
				},
				new int[]{
						0xFFEA4335,
						0xFFFBBC05,
						0xFF4285F4,
						0xFF34A853
				},
				400, 400
		);
	}
	
	/**
	 * Sample loading icon.
	 * Credit to: <div>Icons made by <a href="https://www.flaticon.com/authors/roundicons" title="Roundicons">Roundicons</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/"             title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	 *
	 * @return {@link this}
	 */
	public static PGSVG getSVGLoading(){
		return new PGSVG(
				new String[]{
						"M248,92c-13.6,0-24-10.4-24-24V24c0-13.6,10.4-24,24-24s24,10.4,24,24v44C272,80.8,261.6,92,248,92z",
						"M248,496c-13.6,0-24-10.4-24-24v-44c0-13.6,10.4-24,24-24s24,10.4,24,24v44C272,485.6,261.6,496,248,496z",
						"M157.6,116c-8,0-16-4-20.8-12l-21.6-37.6c-6.4-11.2-2.4-26.4,8.8-32.8s26.4-2.4,32.8,8.8L178.4,80c6.4,11.2,2.4,26.4-8.8,32.8C166.4,114.4,161.6,116,157.6,116z",
						"M360,465.6c-8,0-16-4-20.8-12L317.6,416c-6.4-11.2-2.4-26.4,8.8-32.8c11.2-6.4,26.4-2.4,32.8,8.8l21.6,37.6c6.4,11.2,2.4,26.4-8.8,32.8C368,464.8,364,465.6,360,465.6z",
						"M92,181.6c-4,0-8-0.8-12-3.2l-37.6-21.6c-11.2-6.4-15.2-21.6-8.8-32.8s21.6-15.2,32.8-8.8l37.6,21.6c11.2,6.4,15.2,21.6,8.8,32.8C108,177.6,100,181.6,92,181.6z",
						"M442.4,384c-4,0-8-0.8-12-3.2L392,359.2c-11.2-6.4-15.2-21.6-8.8-32.8c6.4-11.2,21.6-15.2,32.8-8.8l37.6,21.6c11.2,6.4,15.2,21.6,8.8,32.8C458.4,380,450.4,384,442.4,384z",
						"M68,272H24c-13.6,0-24-10.4-24-24s10.4-24,24-24h44c13.6,0,24,10.4,24,24S80.8,272,68,272z",
						"M472,272h-44c-13.6,0-24-10.4-24-24s10.4-24,24-24h44c13.6,0,24,10.4,24,24S485.6,272,472,272z",
						"M53.6,384c-8,0-16-4-20.8-12c-6.4-11.2-2.4-26.4,8.8-32.8l37.6-21.6c11.2-6.4,26.4-2.4,32.8,8.8c6.4,11.2,2.4,26.4-8.8,32.8l-37.6,21.6C62.4,383.2,58.4,384,53.6,384z",
						"M404,181.6c-8,0-16-4-20.8-12c-6.4-11.2-2.4-26.4,8.8-32.8l37.6-21.6c11.2-6.4,26.4-2.4,32.8,8.8s2.4,26.4-8.8,32.8L416,178.4C412,180.8,408,181.6,404,181.6z",
						"M136,465.6c-4,0-8-0.8-12-3.2c-11.2-6.4-15.2-21.6-8.8-32.8l21.6-37.6c6.4-11.2,21.6-15.2,32.8-8.8c11.2,6.4,15.2,21.6,8.8,32.8l-21.6,37.6C152,461.6,144,465.6,136,465.6z",
						"M338.4,116c-4,0-8-0.8-12-3.2c-11.2-6.4-15.2-21.6-8.8-32.8l21.6-37.6c6.4-11.2,21.6-15.2,32.8-8.8c11.2,6.4,15.2,21.6,8.8,32.8L359.2,104C354.4,111.2,346.4,116,338.4,116z",
				},
				new int[]{
						0xFF76FFE5,
						0xFF0DBFBA,
						0xFFBBFFF2,
						0xFF1BCEB8,
						0xFFE1FFF9,
						0xFF26DBC0,
						0xFFF3FFFD,
						0xFF2EE5C6,
						0xFF11AEBA,
						0xFF3BEDCB,
						0xFF0FB8BC,
						0xFF57F7D8,
				},
				496, 496
		);
	}
	
	/**
	 * Sample sand clock / hourglass icon.
	 * Credit to: <div>Icons made by <a href="https://www.flaticon.com/authors/twitter" title="Twitter">Twitter</a> from <a href="https://www.flaticon.com/"             title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/"             title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></div>
	 *
	 * @return
	 */
	public static PGSVG getSVGSandClock(){
		return new PGSVG(
				new String[]{
						"M298.667,256v-0.015c0-28.444,46.166-47.901,71.111-85.319C398.222,128,398.222,28.444,398.222,28.444H256H113.778c0,0,0,99.556,28.444,142.208c24.932,37.433,71.096,56.889,71.096,85.333V256c0,28.459-46.166,47.914-71.096,85.333C113.778,384,113.778,483.556,113.778,483.556H256h142.222c0,0,0-99.556-28.444-142.222C344.832,303.914,298.667,284.459,298.667,256",
						"M298.653,341.333c-14.208,0-29.255-14.222-29.255-28.444c0.81-24.363,0.825-40.477,0.825-56.904c0-45.781,43.15-64.868,69.689-99.541H172.09c26.524,34.674,69.689,53.76,69.689,99.541c0,16.427,0,32.554,0.825,56.904c0,14.222-15.061,28.444-29.283,28.444c-28.444,0-51.114,25.372-56.889,42.667c-14.208,42.667-14.208,99.556-14.208,99.556h99.541h14.237H369.78c0,0,0-56.889-14.222-99.556C349.797,366.705,327.098,341.333,298.653,341.333",
						"M426.667,483.556c0,15.716-12.729,28.444-28.444,28.444H113.778c-15.701,0-28.444-12.729-28.444-28.444c0-15.716,12.744-28.444,28.444-28.444h284.444C413.938,455.111,426.667,467.84,426.667,483.556",
						"M426.667,28.444c0,15.701-12.729,28.444-28.444,28.444H113.778c-15.701,0-28.444-12.744-28.444-28.444S98.077,0,113.778,0h284.444C413.938,0,426.667,12.744,426.667,28.444",
				},
				new int[]{
						0xFFFFE8B6,
						0xFFFFAC33,
						0xFF3B88C3,
						0xFF3B88C3,
				},
				512, 512
		);
	}
	
	//endregion
}
