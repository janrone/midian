package com.domilife.shop.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import com.domilife.shop.R
import com.domilife.shop.activity.JingyingFenxiActivity
import com.domilife.shop.activity.ShopInfoMainActivity
import com.domilife.shop.base.BaseFragment
import com.domilife.shop.view.XYMarkerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import kotlinx.android.synthetic.main.activity_jyfx_content.*
import lecho.lib.hellocharts.model.Axis
import lecho.lib.hellocharts.model.Line
import lecho.lib.hellocharts.model.LineChartData
import lecho.lib.hellocharts.model.PointValue
import java.util.ArrayList
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.XAxis


/**
 * Created by janrone on 2019/3/16.
 */
class JingYingFragment: BaseFragment() {


    private var mTitle :String? = null

    private var mXAxis: XAxis? = null  // X轴
    private var mLeftAxis: YAxis? = null // 左侧Y轴
    private var mRightAxis: YAxis? = null // 右侧Y轴
    private var mLegend: Legend? = null // 图例
    private val mMarkerView: MarkerView? = null

    companion object {
        fun getInstance(title: String): JingYingFragment {
            val fragment = JingYingFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_jyfx_content
    }

    override fun initView() {
        //setChatData()
        initChart(chart_money)
        showLineChart("房间一")
    }

//    private fun setChatData(){
//      //  chart_money
//       // chartView.setOnValueTouchListener(listener);
//        /**
//         * 简单模拟的数据
//         */
//        val values = ArrayList<PointValue>()
//
//        values.add(PointValue(0.0f, 3.0f));
//        values.add( PointValue(1.0f, 1.0f));
//        values.add( PointValue(2.0f, 4.0f));
//        values.add( PointValue(3.0f, 10.0f));
//        //setCubic(true),true是曲线型，false是直线连接
//        var line = Line(values).setColor(Color.parseColor("#DBAB59"))
//        line.setCubic(true)
//        line.setHasLabelsOnlyForSelected(true)
//        var lines = ArrayList<Line>()
//        lines.add(line);
//        var data = LineChartData()
//        data.setLines(lines);
//        var axisX = Axis()
//        axisX.setHasLines(false) //设定是否有网格线
//        var axisY = Axis().setHasLines(true)
//        //设置图标所在位置
//        data.setAxisXBottom(axisX);
//        data.setAxisYLeft(axisY);
//        //将数据添加到View中
//        chart_money.setLineChartData(data);
//
//    }

    /**
     * 初始化图表
     * @param lineChart  需要初始化的图表
     */
    private fun initChart(lineChart: LineChart) {

        run {
            // // Chart Style // //

            // background color
            lineChart.setBackgroundColor(Color.WHITE)

            // disable description text
            lineChart.getDescription().setEnabled(false)

            // enable touch gestures
            lineChart.setTouchEnabled(true)
            lineChart.setScaleEnabled(false)

            // set listeners
            //lineChart.setOnChartValueSelectedListener(this)
            //chalineChartrt.setDrawGridBackground(false)

            // create marker to display box when values are selected
            val mv = XYMarkerView(activity)

            // Set the marker to the chart
            mv.setChartView(lineChart)
            lineChart.setMarker(mv)

            // enable scaling and dragging
            //chart.setDragEnabled(true)
            //chart.setScaleEnabled(true)

            // force pinch zoom along both axis
            lineChart.setPinchZoom(false)
            lineChart.legend.isEnabled = false

        }

        val xAxis: XAxis
        run {
            // // X-Axis Style // //

            xAxis = lineChart.getXAxis()

            xAxis.xOffset= 0F
            xAxis.yOffset =0F
            // vertical grid lines
            //xAxis.enableGridDashedLine(10f, 10f, 0f)

            xAxis.setDrawGridLines(false)
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        }

        val yAxis: YAxis
        run {
            // // Y-Axis Style // //
            yAxis = lineChart.getAxisLeft()
            xAxis.xOffset= 0F
            xAxis.yOffset =0F

            // disable dual axis (only use LEFT axis)
            lineChart.getAxisRight().setEnabled(false)

            // horizontal grid lines
            yAxis.enableGridDashedLine(10f, 10f, 0f)

        }

        /* 图表设置 */
        //是否展示网格线
        //        lineChart.setDrawGridBackground(true);
        //        lineChart.setGridBackgroundColor(Color.CYAN);


        //        mXAxis.setAxisMinimum(5f);
        //        mXAxis.setAxisMaximum(10f);



//        // 设置图表数据点击回调
//        lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
//            override fun onValueSelected(e: Entry, h: Highlight) {
//                Toast.makeText(activity, "用电量：" + e.y, Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected() {
//
//            }
//        })

    }

    /**
     * 曲线初始化设置
     * 一个LineDataSet 代表一条曲线
     * @param lineDataSet 线条
     */
    private fun initLineDataSet(lineDataSet: LineDataSet) {
        lineDataSet.color = Color.parseColor("#DBAB59")
        lineDataSet.setCircleColor(Color.parseColor("#DBAB59"))
        lineDataSet.lineWidth = 3f
        lineDataSet.circleRadius = 6f

        // 设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false)

        lineDataSet.valueTextSize = 12f

        // 设置折线图填充
        lineDataSet.setDrawFilled(false)
        lineDataSet.formLineWidth = 1f
        lineDataSet.formSize = 15f

        // 设置曲线展示位圆滑曲线（默认是折线）
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
    }

    private fun showLineChart(name: String) {
        val entries = ArrayList<Entry>()
        for (i in 0..19) {
            val entry = Entry(i.toFloat(), Math.random().toFloat() * 10000)
            entries.add(entry)
        }

        // 每一个LineDataSet 代表一条线
        val lineDataSet = LineDataSet(entries,"")
        lineDataSet.setDrawValues(false)
        initLineDataSet(lineDataSet)

        val lineData = LineData(lineDataSet)
        chart_money?.setData(lineData)
    }
}

