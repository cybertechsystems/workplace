var globaltitle;
var OrderHistory =
{
	chartOptions :
	{
		chart :
		{
			renderTo : null, // to be set
			type : 'spline'
		},
		credits :
		{
			enabled : false
		},
		title :
		{
			text : null, // to be set
			margin : 20
		},
		subtitle :
		{
			text : null, // to be set
		},
		tooltip :
		{
			shared : true,
		},
		xAxis :
		{
			type : 'datetime',
			title :
			{
				text : null
			}
		},
		yAxis :
		{
			title :
			{
				text : null
			}
		},
		plotOptions :
		{
			spline :
			{
				marker :
				{
					enabled : true,
					radius : 5,
					states :
					{
						hover :
						{
							enabled : true,
							radius : 5
						}
					}
				},
				shadow : false,
				states :
				{
					hover :
					{
						lineWidth : 1
					}
				}
			},
			series :
			{
				cursor : 'pointer',
				point :
				{
					events :
					{
						click : function()
						{
							var unitstToDisplay = globaltitle;
							var regExp = /\(([^)]+)\)/;
							var matches = regExp.exec(unitstToDisplay);
							var display = '';
							if(matches != null){
								display = '('+matches[1]+')';
							}
							hs.htmlExpand(null,
							{
								pageOrigin :
								{
									x : this.pageX,
									y : this.pageY
								},
								headingText : this.series.name,
								maincontentText : Highcharts.dateFormat('%A, %b %e, %Y', this.x)
										+ ':<br/> ' + this.y + ' ' + display,
								width : 240
							});
						}
					}
				},
				marker :
				{
					lineWidth : 1
				}
			},
		},
		series : [
		{
			name : null, // to be set
			data : [ 1, 2, 3, 4 ]
		} ]
	},

	chart : null, // to be set by drawChart()

	drawChart : function(container, title, yTitle, data)
	{
		this.chartOptions.chart.renderTo = container;
		this.chartOptions.title.text = title;
		this.chartOptions.yAxis.title.text = yTitle;
		this.chartOptions.series[0].name = yTitle;
		this.chartOptions.series[0].data = data;
		globaltitle = yTitle;
		this.chart = new Highcharts.Chart(this.chartOptions);
	}
};

