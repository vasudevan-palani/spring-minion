/**
 * @license =========================================================
 *          bootstrap-datetimepicker.js
 *          http://www.eyecon.ro/bootstrap-datepicker
 *          ========================================================= Copyright
 *          2012 Stefan Petre
 * 
 * Contributions: - Andrew Rowls - Thiago de Arruda
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License. =========================================================
 */

var minionModule = angular.module('minion', [ 'ui.bootstrap' ]);

minionModule
		.config([
				'$httpProvider',
				function($httpProvider) {
					$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
				} ]);

minionModule.filter('unsafe', function($sce) {
	return function(val) {
		return $sce.trustAsHtml(val);
	};
});

minionModule
		.directive(
				'ninjaPanel',
				function() {
					return {
						restrict : 'E',
						scope : {
							title : '=',
							close : '&'
						},
						transclude : true,
						template : '<div class="panel panel-default">\
                       <div class="panel-heading">\
                           {{title}}<button ng-if="closable()" ng-click="close()" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>\
                       </div>\
                       <div class="panel-body">\
                       <div ng-transclude></div>\
                       </div>\
                    </div>',
						link : function(scope, elm, attrs) {
							scope.closable = function() {
								if (angular.isUndefined(attrs.close)) {
									return false;
								}
								return true;
							}

							if (!angular.isUndefined(attrs.category)) {
								$(elm).find("div.panel").each(function(i, e) {
									$(e).removeClass('panel-default');
									$(e).addClass('panel-' + attrs.category);
								});
							}
						}
					}
				});

minionModule
		.directive(
				'ninjaCalendar',
				function() {
					return {
						require : 'ngModel',

						restrict : 'E',
						scope : {
							dateobj : '=ngModel',
							dateformat : '@format',
							disabled : '='
						},

						link : function(scope, element, attrs, ngModel) {

							var htmlcontent = '<div style="width:200px" class=" text-left input-group input-append date">\
                    <input data-format="'
									+ scope.dateformat
									+ '" maxlength="20" ng-model="dateobj" class="form-control input-group timestamp" type="text"></input>\
                    <span class="input-group-addon add-on">\
                      <i date-icon="glyphicon glyphicon-calendar" time-icon="glyphicon glyphicon-time" class="glyphicon glyphicon-calendar">\
                      </i>\
                    </span></div>';
							element.html(htmlcontent);

							if (!angular.isUndefined(attrs.pasteFormat)) {
								$(element)
										.find('input')
										.on(
												'paste',
												function() {
													var e = this;
													setTimeout(
															function() {

																var text = $(e)
																		.val();
																console
																		.log(text);
																var date1 = Date
																		.parseExact(
																				text,
																				attrs.pasteFormat);

																if (attrs.dateTime == false) {
																	$(e)
																			.val(
																					date1
																							.toString("yyyy-MM-dd"));
																	$(e)
																			.trigger(
																					'change');
																} else {
																	$(e)
																			.val(
																					date1
																							.toString("yyyy-MM-dd HH:mm:ss"));
																	$(e)
																			.trigger(
																					'change');
																}
															}, 100)
												});
							}

							scope.$watch("dateobj", function() {
								$(element).find("input").each(function(i, e) {
									$(e).val(scope.dateobj);
									$(e).trigger('change');

								});
							});

							var options = {};
							if (attrs.time == "false") {
								options.pickTime = false;
							}
							$(element).find("div").each(function(i, e) {
								$(e).datetimepicker(options);
							});

							$(element).find("div").each(function(i, e) {
								$(e).on("changeDate", function() {
									$(e).find("input").each(function(q, w) {
										ngModel.$setViewValue($(w).val());
										ngModel.$render();
									});
									$(e).data("datetimepicker").hide();
								});

							});
						}
					}
				});

minionModule.directive('ninjaGraph', function() {
	return {
		restrict : 'A',
		scope : {
			graphdata : '='
		},
		link : function(scope, elm, attrs) {

		}
	}
});

minionModule.directive('ninjaIcon', function() {
	return {
		restrict : 'A',
		scope : false,
		link : function(scope, elm, attrs) {
			if (attrs.ninjaBtn != "") {
				$(elm).prepend(
						"<span class='glyphicon glyphicon-" + attrs.ninjaIcon
								+ "'></span> ");

			}
		}
	}
});

minionModule.directive('ninjaBtn', function() {
	return {
		restrict : 'A',
		scope : false,
		link : function(scope, elm, attrs) {
			if (attrs.ninjaBtn != "") {
				$(elm).prepend(
						"<span class='glyphicon glyphicon-" + attrs.ninjaBtn
								+ "'></span> ");
				$(elm).addClass("btn btn-sm ");
			}

			if (!angular.isUndefined(attrs.category)) {
				$(elm).addClass("btn-" + attrs.category);
			} else {
				$(elm).addClass("btn-primary");
			}
		}
	}
});

minionModule
		.service(
				'$ninjaUtils',
				function($rootScope, $sce, $http) {
					this.alert = function(str) {
						$rootScope.info = str;
						$rootScope.alert = "";

						$("#modal_info").modal();
					};
					this.ajaxCall = function(url, data, successFunction,
							errorFunction, silent) {

						if (angular.isUndefined($rootScope.ajaxCount)) {
							$rootScope.ajaxCount = 0;
						}

						$rootScope.ajaxCount++;
						$rootScope.progressbar_width = Math
								.floor(100 / ($rootScope.ajaxCount * 2));

						if (silent == undefined) {
							silent = false;
						}

						if (!silent) {
							ajax_msg = "Please wait";

						}
						$rootScope.msg = "Please wait..";
						$http
								.post(url, data)
								.success(
										function(data, status, headers, config) {
											data = data;
											$rootScope.ajaxCount--;
											if ($rootScope.ajaxCount == 0) {
												$rootScope.progressbar_width = 100;
											} else {
												$rootScope.progressbar_width = Math
														.floor(100 / ($rootScope.ajaxCount * 2));
											}

											if ('errorcode' in data
													&& data.errorcode == "0") {
												$rootScope.warnMsg = data.warnMsg;
												$rootScope.infoMsg = data.infoMsg;
												$rootScope.alertMsg = data.alertMsg;

												if ('redirectUrl' in data
														&& data.redirectUrl != null
														&& data.redirectUrl != "") {
													console
															.log(data.redirectUrl);
													window.location = data.redirectUrl;
												}
												successFunction(data);
											} else if ('errorcode' in data
													&& data.errorcode != "0") {
												$rootScope.errorMsg = data.errorMsg;
												if (errorFunction != undefined) {
													errorFunction(data);
												}
											}
										})
								.error(
										function(data, status, headers, config) {
											$rootScope.ajaxCount--;
											if ($rootScope.ajaxCount == 0) {
												$rootScope.progressbar_width = 100;
											} else {
												$rootScope.progressbar_width = 100 / ($rootScope.ajaxCount * 2);
											}

											$rootScope.error = config;
											$("#modal_error").modal();

										});
					};

					this.error = function(str) {
						$rootScope.error = str;

						$("#modal_error").modal();
					};

				});

minionModule
		.directive(
				'typeahead',
				function($timeout, $http, $ninjaUtils, $rootScope) {

					return {

						restrict : 'AEC',

						scope : {

							server : '@',
							plugin : '@',
							metric : '@',
							modeldisplay : '=',

							modelret : '=',
							servermodel : '=',
							pluginmodel : '=',
							metricmodel : '=',
							serveridmodel : '=',
							pluginopidmodel : '='

						},

						link : function(scope, elem, attrs) {

							scope.current = 0;

							scope.selected = false;

							scope.da = function(txt) {

								scope.ajaxClass = 'loadImage';
								if ((!angular.isUndefined(scope.modeldisplay))
										&& scope.modeldisplay != "") {
									$ninjaUtils
											.ajaxCall(
													'/Ninja/rest/metric/auto',
													{
														username_e : $rootScope.username_e,
														password_e : $rootScope.password_e,
														tag : scope.modeldisplay
													},
													function(data) {

														console.log(data.data);
														// Append to the graph
														// data
														//
														scope.TypeAheadData = data.data.autoList;
													});

								}
								// scope.TypeAheadData = [
								// {serverName:'Varnish1',pluginName:'CPUCheck',metricName:'cpuload',serverId:'1',pluginId:'2'},
								// {serverName:'Balon',metricName:'diskusage',pluginName:"DiskCheck",serverId:'2',pluginId:'3'}
								// ];

							}

							scope.handleSelection = function(item) {
								scope.modelret = item.serverName + "/"
										+ item.pluginName + "/"
										+ item.metricName;
								scope.servermodel = item.serverName;
								scope.serveridmodel = item.serverId;
								scope.metricmodel = item.metricName;
								scope.pluginmodel = item.pluginName;
								scope.pluginopidmodel = item.pluginopId;
								scope.modeldisplay = item.serverName + "/"
										+ item.pluginName + "/"
										+ item.metricName;
								scope.searchtag = item.serverName + "%"
										+ item.pluginName + "%"
										+ item.metricName;
								scope.current = 0;
								scope.selected = true;
							}

							scope.isCurrent = function(index) {

								return scope.current == index;

							}

							scope.setCurrent = function(index) {

								scope.current = index;

							}

						},

						template : '<input type="text" class="form-control dashboard-input" ng-model="modeldisplay" ng-KeyPress="da(modeldisplay)"  ng-keydown="selected=false" style="width:100%;" ng-class="ajaxClass">'
								+

								'<div class="list-group overlap" ng-hide="!modeldisplay.length || selected" style="width:100%">'
								+

								'<a href="#" class="list-group-item noTopBottomPad" ng-repeat="item in TypeAheadData|filter: modeldisplay  track by $index" '
								+

								'ng-click="handleSelection(item)" style="cursor:pointer" '
								+

								'ng-class="{active:isCurrent($index)}" '
								+

								'ng-mouseenter="setCurrent($index)">'
								+

								' {{item[server]}} / {{item[plugin]}} / <i>{{item[metric]}} </i>'
								+

								'</a> ' +

								'</div>' +

								'</input>'

					};

				});

minionModule.controller('LoginController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.data = {};

	$scope.login = function() {
		$ninjaUtils.ajaxCall('/users/login', $scope.data, function(data) {
			$rootScope.creds = data.data;
		});
	}
});

minionModule.controller('NavController', function($scope, $rootScope,
		$ninjaUtils, $log) {
	$scope.data = {};
	$rootScope.selects = {};
	$scope.init = function() {

		$rootScope.empId = document.getElementsByName("empId")[0].value;
		$rootScope.password = document.getElementsByName("password")[0].value;

		if (angular.isUndefined($rootScope.empId)
				|| angular.isUndefined($rootScope.password)) {
			window.location = "/";
		}

		// if($rootScope.)
		// ret = $rootScope.getList(['Users','Pos','Projects','Statuses'],true);
	}

	$rootScope.getList = function(items, refresh) {
		var results = {};
		if (refresh) {
			$ninjaUtils.ajaxCall('/minion/selects/index.json', {
				'items' : items
			}, function(data) {
				for (item in data.data) {
					$rootScope.selects[item] = data.data[item];
					results[item] = $rootScope.selects[item];
				}
			});
		} else {
			for (item in items) {
				if ($rootScope.selects[item]) {
					results[item] = $rootScope.selects[item];
				}
			}
		}
		return results;
	}

	$scope.selectedItem = "";

	$scope.selectMainTab = function(name) {
		$log.debug("selectMainTab: " + name);
		$rootScope.maintab = name;
	}

	$scope.selectItem = function(name) {
		$rootScope.selectedItem = name;
		$rootScope.$broadcast(name, {});
	}

	$scope.isMainTab = function(name) {
		return $rootScope.maintab == name;
	}

	$scope.isSelectedItem = function(name) {
		return $rootScope.selectedItem == name;
	}

});

minionModule.controller('ProjectsController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.data = {};

	$scope.selectedItem = "";

	$scope.search = function() {
		$ninjaUtils.ajaxCall('/minion/projects/search.json', $scope.data,
				function(data) {
					$scope.results = data.data;
				});
	}
});

minionModule.controller('EffortsController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.add = {
		data : {}
	};
	$scope.search = {
		data : {}
	};
	$scope.edit = {
		data : {}
	};

	$scope.selectedItem = "";

	$scope.startdatep = {
		opened : false
	};

	$scope.enddatep = {
		opened : false
	};

	$rootScope.$on('addefforts', function(event, args) {
		$ninjaUtils.ajaxCall('/allocations/index', {
			"empId" : $rootScope.empId,
			"password" : $rootScope.password
		}, function(data) {
			$scope.allocations = data.object;
		});
	});

	$scope.selectAllocation = function(allocation) {
		$scope.selectedAllocation = allocation;
		
		$scope.startdate = new Date();
		var startdate1 = new Date();
		startdate1.setDate(startdate1.getDate() + 7);
		$scope.enddate = startdate1;
	}

	$scope.toDate = function(dateStr) {
		var parts = dateStr.split("-");
		return new Date(parts[0], parts[1] - 1, parts[2]);
	}

	$scope.search.submit = function() {
		$ninjaUtils.ajaxCall('/minion/efforts/search.json', $scope.search.data,
				function(data) {
					$scope.results = data.data;
				});
	}
	$scope.dateRange = [];
	$scope.$watch('startdate', function(newVal, oldVal) {
		$scope.updateDates();
	});

	$scope.$watch('enddate', function(newVal, oldVal) {
		$scope.updateDates();
	});

	Date.prototype.yyyymmdd = function() {
		var yyyy = this.getFullYear().toString();
		var mm = (this.getMonth() + 1).toString(); // getMonth() is zero-based
		var dd = this.getDate().toString();
		return yyyy + "-" + (mm[1] ? mm : "0" + mm[0]) + "-"
				+ (dd[1] ? dd : "0" + dd[0]); // padding
	};

	$scope.updateDates = function() {

		if(angular.isUndefined($scope.selectedAllocation)){
			return;
		}
		var reqdata = {
				"allocationId" : $scope.selectedAllocation.id,
				"empId" : $rootScope.empId,
				"password" : $rootScope.password,
				"startDate" : $scope.startdate,
				"endDate":$scope.enddate
			};

			$ninjaUtils.ajaxCall('/efforts/getEfforts', reqdata, function(data) {
				$scope.effortData= data.object;

				if ($scope.startdate != null && $scope.enddate != null
						&& $scope.startdate <= $scope.enddate) {
					$scope.dateRange.splice(0, $scope.dateRange.length);

					var start = new Date($scope.startdate);
					var end = new Date($scope.enddate);

					while (start <= end) {
						var effortDataItem = {date:start.yyyymmdd(),effort:"0"};
						for (var int = 0; !angular.isUndefined($scope.effortData) && int < $scope.effortData.length; int++) {

							if($scope.effortData[int].date == start.yyyymmdd()){
								effortDataItem.effort=$scope.effortData[int].effort;
							}
						}
						$scope.dateRange.push(effortDataItem);						
						
						var newDate = start.setDate(start.getDate() + 1);
						start = new Date(newDate);
					}

			
			}	
			
		});
	}

	$scope.addEfforts = function() {
		var reqdata = {
			"allocationId" : $scope.selectedAllocation.id,
			"empId" : $rootScope.empId,
			"password" : $rootScope.password,
			"efforts" : $scope.dateRange
		};

		$ninjaUtils.ajaxCall('/efforts/add', reqdata, function(data) {
			$scope.results = data.data;
		});
	}

});

minionModule.controller('AlertController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.data = {};

	$scope.selectedItem = "";
	$scope.msg = "";

	$scope.isAlertVisible = function() {
		return $rootScope.msg != "";
	}

	$scope.hideAlert = function() {
		$rootScope.msg = "";
	}

	$scope.showAlert = function(msg) {
		$rootScope.msg = msg;
	}

});
minionModule.controller('InvoicesController', function($scope, $rootScope,
		$ninjaUtils, $log) {
	$scope.data = {};

	$scope.selectedPO = {};

	$scope.selectedItem = "";

	$scope.search = function() {
		$ninjaUtils.ajaxCall('/minion/invoices/search.json', $scope.data,
				function(data) {
					$scope.results = data.data;
				});
	}

	$scope.add = function() {
		$ninjaUtils.ajaxCall('/minion/invoices/add.json', $scope.data,
				function(data) {
					$scope.results = data.data;
				});
	}

	$scope.logItem = function(item) {
		$log.debug(item);
	}

	$scope.selectPO = function(po) {
		for (po in $rootScope.selects.Pos) {
			if ($rootScope.selects.Pos[po].id == $scope.data.po_id) {
				$scope.selectedPO = $rootScope.selects.Pos[po];
			}
		}
		$ninjaUtils.ajaxCall('/minion/purchaseOrders/get.json', {
			'id' : $scope.selectedPO.id
		}, function(data) {
			$scope.data.invoice_users = data.data[0].po_users;
		});
		$ninjaUtils.ajaxCall('/minion/invoices/getTotalInvoicesByPo.json', {
			'po_id' : $scope.selectedPO.id
		}, function(data) {
			$scope.po_used = data.data.sum;
		});

	}

	$scope.calculateTotal = function(userItem) {

		userItem.total = userItem.hours * userItem.billing_rate;
		console.log(userItem);
	}

});

minionModule.controller('UsersController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.data = {};

	$scope.search = {
		data : {}
	};

	$scope.selectedItem = "";

	$scope.addUser = function() {
		$ninjaUtils.ajaxCall('/minion/users/add.json', $scope.data, function(
				data) {

		});
	}

	$scope.searchUser = function() {
		$ninjaUtils.ajaxCall('/minion/users/search.json', $scope.search.data,
				function(data) {
					$scope.results = data.data;
				});
	}
});

minionModule.controller('PurchaseOrdersController', function($scope,
		$rootScope, $ninjaUtils) {
	$scope.data = {};

	$scope.selectedItem = "";
	$scope.data.users = [];

	$scope.search = {};
	$scope.edit = {
		data : {
			po_users : {}
		}
	};

	$scope.$on('userslist', function(event, args) {
		$scope.userslist = args;
	});

	$scope.init = function() {

	}

	$scope.search = function() {
		$ninjaUtils.ajaxCall('/minion/purchaseOrders/search.json', {
			'po_number' : $scope.search.po_number
		}, function(data) {
			if (!angular.isUndefined(data.data[0])) {
				$scope.edit.data = data.data[0];
			}
		});
	}

	$scope.isEditItemSelected = function() {
		if (angular.isUndefined($scope.edit.data.po_number)) {
			return false;
		}
		return true;
	}

	$scope.calculateTotal = function(userItem) {

		userItem.total = userItem.hours * userItem.billing_rate;
		console.log(userItem);
	}

	$scope.edit.addUser = function() {
		console.log("Pushing");
		$scope.edit.data.po_users.push({});
	}

	$scope.edit.deleteUserItem = function(index) {
		console.log(index);
		$scope.edit.data.po_users.splice(index, 1);
	}

	$scope.addUser = function() {
		console.log("Pushing");
		$scope.data.users.push({});
	}

	$scope.deleteUserItem = function(index) {
		console.log(index);
		$scope.data.users.splice(index, 1);
	}

	$scope.addPO = function() {
		$ninjaUtils.ajaxCall('/minion/purchaseOrders/add.json', $scope.data,
				function(data) {
					$scope.results = data.data;
				});
	}

	$scope.update = function() {
		$ninjaUtils.ajaxCall('/minion/purchaseOrders/update.json',
				$scope.edit.data, function(data) {
				});
	}
});

minionModule.controller('NavBarController', function($scope, $rootScope,
		$ninjaUtils) {
	$scope.data = {};

	$scope.selectedItem = "";

	$scope.domainMngt = {};
	$scope.serverMngt = {};
	$scope.pluginMngt = {};
	$scope.applicationMngt = {};
	$scope.dashboardMngt = {};

	$scope.home = {};

	$scope.selectedItem = "home";

	$scope.home.click = function() {
		$scope.selectedItem = "home";
		$rootScope.$broadcast("homeClicked", {});
	}

	$scope.home.isSelected = function() {
		return $scope.selectedItem == "home";
	}

	$scope.domainMngt.click = function() {
		$scope.selectedItem = "domain";
		$rootScope.$broadcast("domainClicked", {});
	}

	$scope.domainMngt.isSelected = function() {
		return $scope.selectedItem == "domain";
	}

	$scope.serverMngt.click = function() {
		$scope.selectedItem = "server";
		$rootScope.$broadcast("serverClicked", {});
	}

	$scope.serverMngt.isSelected = function() {
		return $scope.selectedItem == "server";
	}

	$scope.pluginMngt.click = function() {
		$scope.selectedItem = "plugin";
		$rootScope.$broadcast("pluginClicked", {});
	}

	$scope.pluginMngt.isSelected = function() {
		return $scope.selectedItem == "plugin";
	}

	$scope.applicationMngt.click = function() {
		$scope.selectedItem = "app";
		$rootScope.$broadcast("appClicked", {});
	}

	$scope.applicationMngt.isSelected = function() {
		return $scope.selectedItem == "app";
	}

	$scope.dashboardMngt.click = function() {
		$scope.selectedItem = "dashboard";
		$rootScope.$broadcast("dashboardClicked", {});
	}
	$scope.dashboardMngt.isSelected = function() {
		return $scope.selectedItem == "dashboard";
	}

});