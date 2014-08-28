package it.ismb.pertlab.pwal.highcharts.bean;


import org.codehaus.jackson.map.annotate.JsonRootName;

import java.util.List;

@JsonRootName("dataBean")
public class DataBean {
    private String divId;
    private String title;
    private String yAxisTitle;
    private String xAxisTitle;
    private List<long[]> categories;
    private List<SeriesBean> series;


    public DataBean(String divId, String title, String yAxisTitle, String xAxisTitle, List<long[]> categories, List<SeriesBean> series) {
        this.setDivId(divId);
        this.setTitle(title);
        this.setyAxisTitle(yAxisTitle);
        this.setxAxisTitle(xAxisTitle);
        this.setCategories(categories);
        this.setSeries(series);
    }



	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getyAxisTitle() {
        return yAxisTitle;
    }

    public void setyAxisTitle(String yAxisTitle) {
        this.yAxisTitle = yAxisTitle;
    }

    public List<long[]> getCategories() {
        return categories;
    }

    public void setCategories(List<long[]> categories2) {
        this.categories = categories2;
    }

    public List<SeriesBean> getSeries() {
        return series;
    }

    public void setSeries(List<SeriesBean> series) {
        this.series = series;
    }

    public String getDivId() {
        return divId;
    }

    public void setDivId(String divId) {
        this.divId = divId;
    }

    public String getxAxisTitle() {
        return xAxisTitle;
    }

    public void setxAxisTitle(String xAxisTitle) {
        this.xAxisTitle = xAxisTitle;
    }
}
