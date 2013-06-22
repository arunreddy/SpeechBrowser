<html>
<head>
<meta name="layout" content="main" />
<r:require modules="bootstrap" />
<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'inlineplayer.css')}"
	type="text/css">

<link rel="stylesheet"
	href="${resource(dir: 'css', file: 'flashblock.css')}" type="text/css">
</head>
<body>

	<div class="container-fluid">
		<div class="row-fluid">
			<h4>Synchronize</h4>
			<hr />
			<button id="synchronize" class="btn btn-large btn-primary"
				type="button">Synchronize Now..!</button>
			<div id="progressbar"><div class="progress-label">Loading...</div></div>
		</div>
		<!-- end row-fluid -->
	</div>

	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$('div.pagination').addClass('pagination-mini')
									.addClass('pagination-right').css("margin",
											"0px").css("margin-top", "30px");
							$('hr').css("margin", "5px");

							$("#progressbar").progressbar({
								value : false
							});

							$('#progressbar').hide();

							$('#synchronize')
									.click(
											function() {

												$('#progressbar').show();
												var progressbar = $("#progressbar"), progressLabel = $(".progress-label");
												progressbar
														.progressbar({
															value : false,
															change : function() {
																progressLabel
																		.text(progressbar
																				.progressbar("value")
																				+ "%");
															},
															complete : function() {
																progressLabel
																		.text("Complete!");
															}
														});
												function progress() {

													$
															.ajax({
																url : "/SpeechBrowser/statusServlet",
																success : function(
																		msg) {

																	var data = Math.round(msg.count*100/msg.total)
																	console
																			.log(data);

																	var val = progressbar
																			.progressbar("value") || 0;
																	progressbar
																			.progressbar(
																					"value",
																					data);
																	if (val < 99) {
																		setTimeout(
																				progress,
																				100);
																	}
																}
															});
												}
												setTimeout(progress, 3000);
											});

						});
	</script>
</body>
</html>