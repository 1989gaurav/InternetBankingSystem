 Ext.BLANK_IMAGE_URL = 'images/s.gif';
var passWindow;
var chPassWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Profile Password',
			                layout:'form',
			                defaults: {autoHeight: true,inputType: 'password'},
			                defaultType: 'textfield',
			
			                items: [{
			                    fieldLabel: '<b>Current Password</b>',
			                    id: 'cpass',
								name:'cpass'
			                },{
			                    fieldLabel: '<b>New Password</b>',
			                    id: 'npass',
								name:'npass'
			                    },{
			                    fieldLabel: '<b>Confirm Password</b>',
			                    id: 'cnpass',
								name:'cnpass'
			                    }]
			            },{
			                title:'Transaction Password',
			                layout:'form',
			                defaults: {autoHeight: true,inputType: 'password'},
			                defaultType: 'textfield',
			
			                items: [{
			                    fieldLabel: '<b>Current Password</b>',
			                    id: 'cpass_t',
								name:'cpass_t'
			                },{
			                    fieldLabel: '<b>New Password</b>',
			                    id: 'npass_t',
								name:'npass_t'
			                    },{
			                    fieldLabel: '<b>Confirm Password</b>',
			                    id: 'cnpass_t',
								name:'cnpass_t'
			                    }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Change',
						handler: function(){
								var pwd1 = document.getElementById('npass').value;
								var pwd2 = document.getElementById('cnpass').value;
								var pwd3_el = document.getElementById('npass_t');
								var pwd3,pwd4;
								if(pwd3_el!=null)
									pwd3 = pwd3_el.value;
								var pwd4_el = document.getElementById('cnpass_t');
								if(pwd4_el!=null)
									pwd4 = pwd4_el.value;
								if(pwd1!=pwd2)
								{
									Ext.MessageBox.alert('Password Mismatch','Profile New Password & Confirm Password entries are not the same');
									chPassWin.getForm().reset();
								}
								else if(pwd3!=pwd4)
								{
									Ext.MessageBox.alert('Password Mismatch','Transaction New Password & Confirm Password entries are not the same');
									chPassWin.getForm().reset();
								}
								else
								{
									chPassWin.getForm().submit({
										method: 'POST',
										waitMsg: 'Contacting Server... Password Change in Progress... Please wait...',
										url: 'ChangePassword',
										success: function(){
											passWindow.hide();
											Ext.MessageBox.alert('Success','Password Changed Successfully');
										},
										failure: function(){
											Ext.MessageBox.alert('Failure','Password could not be changed... Try later or contact Administrator');
										}
									});
								}
						}
			        },{
			            text: 'Reset',
						handler : function(){
							chPassWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        passWindow.hide();
                    }
					}]
			    }) 

var delFavStore = new Ext.data.SimpleStore({
                            fields: ['fav']
                        });
				
				
var manFavWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Add Favourites',
			                layout:'form',
			                defaults: {autoHeight: true},
			                xtype: 'checkboxgroup',	
							itemCls: 'x-check-group-alt',
							columns: 2,
							vertical: true,
			                items: [{
			                    boxLabel: '<b>Change Password</b>',
			                    name:'fav1'
			                },{
			                    boxLabel: '<b>Manage Favorites</b>',
			                    name:'fav2'
			                    },{
			                    boxLabel: '<b>Control Manager</b>',
			                    name:'fav3'
			                    },{
			                    boxLabel: '<b>Account Statements</b>',
			                    name:'fav4'
			                    },{
			                    boxLabel: '<b>Draft Status</b>',
			                    name:'fav5'
			                    },{
			                    boxLabel: '<b>Fund Transfer History</b>',
			                    name:'fav6'
			                    },{
			                    boxLabel: '<b>Bills History</b>',
			                    name:'fav7'
			                    },{
			                    boxLabel: '<b>View Standing Instruction</b>',
			                    name:'fav8'
			                    },{
			                    boxLabel: '<b>Find/Search</b>',
			                    name:'fav9'
			                    },{
			                    boxLabel: '<b>Help</b>',
			                    name:'fav10'
			                    }]
			            },{
			                title:'Delete Favourites',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'combo',
			
			                items: [{
			                    fieldLabel: '<b>Current Favorite List</b>',
			                    id: 'cfav_id',
								name:'cfav',
								store: delFavStore,
								valueField:'fav',
								displayField:'fav',
								typeAhead: true,
		                        mode: 'local',
		                        triggerAction: 'all',
		                        emptyText:'Select Favourite Task',
		                        selectOnFocus:true,
		                        width:190,
								listeners:{
								beforerender: function(){
									Ext.Ajax.request({
										method: 'POST',
										url: 'GetFavouriteList',
										success: function(response,request){
											var favData = Ext.decode(response.responseText);
											delFavStore.loadData(favData.data);
										},
										failure: function(){
											Ext.MessageBox.alert('Failure','Could not load your Favorites. Please re-login to the system');
										}
									});
								}
								}
			                }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Manage',
						handler: function(){
								
								manFavWin.getForm().submit({
									method: 'POST',
									waitMsg: 'Contacting Server... Adding/Deleting you Favorites... Please wait...',
									url: 'ManageFavorite',
									success: function(){
										Ext.MessageBox.alert('Success','Favorites Added/Removed Successfully');
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Operation could not be completed... Try later or contact Administrator');
									}
								});
						}
			        },{
			            text: 'Reset',
						handler : function(){
							manFavWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        favWindow.hide();
                    }
					}]
			    }) 


			


var conWindow;

var controlWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Map Account',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
			                    fieldLabel: '<b>Account Number</b>',
			                    id: 'mapAC_id',
								name:'mapAC'
			                }]
			        }]},
			
			        buttons: [{
			            text: 'Map',
						handler: function(){
								var acnum = document.getElementById('mapAC_id').value;
								if(acnum == '')
								{
									Ext.MessageBox.alert('Input Validation','Account Number Field cannot be empty');
								}
								else if(isNaN(acnum))
								{
									Ext.MessageBox.alert('Input Validation','Account Number Field should be numeral');
								}
								else
								{
									controlWin.getForm().submit({
									method: 'POST',
									waitMsg: 'Contacting Server... Operation in Progress... Please wait...',
									url: 'ControlManager',
									success: function(){
										conWindow.hide();
										Ext.MessageBox.alert('Success','Account Mapped to User Successfully');
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Operation could not be completed... Try later or contact Administrator');
									}
									});
								}
						}
			        },{
			            text: 'Reset',
						handler : function(){
							controlWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        conWindow.hide();
                    }
					}]
			    }) 



	Ext.apply(Ext.form.VTypes, {
    daterange : function(val, field) {
        var date = field.parseDate(val);

        if(!date){
            return;
        }
        if (field.startDateField && (!this.dateRangeMax || (date.getTime() != this.dateRangeMax.getTime()))) {
            var start = Ext.getCmp(field.startDateField);
            start.setMaxValue(date);
            start.validate();
            this.dateRangeMax = date;
        } 
        else if (field.endDateField && (!this.dateRangeMin || (date.getTime() != this.dateRangeMin.getTime()))) {
            var end = Ext.getCmp(field.endDateField);
            end.setMinValue(date);
            end.validate();
            this.dateRangeMin = date;
        }
        /*
         * Always return true since we're only using this vtype to set the
         * min/max allowed values (these are tested for after the vtype test)
         */
        return true;
    }
});


var storeStmt = new Ext.data.Store({
        // load using HTTP
        proxy: new Ext.data.HttpProxy({url: 'SendAccount',method:'GET'}),
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.JsonReader({
               // records will have an "Item" tag
               totalProperty: "size",    // The property which contains the total dataset size (optional)
			   root: "data",                // The property which contains an Array of row objects
			   id: "id"
           },[{name: 'acnum'}]),
        autoLoad: true
    });
	

	function doOperation(btn)
	{
		var acStatnum = document.getElementById('acStat_id').value;
		if(btn=='yes')
			window.print();
		else if(btn == 'no')
			window.open("content/"+acStatnum+".pdf");
	}
 
 
 function saveDraft(btn)
 {
	var acDraft = document.getElementById('account_id').value;
	if(btn=='yes')
		window.open("content/Draft"+acDraft+".pdf");
 }
	
function saveBill(btn)
 {
	var acDraft = document.getElementById('ac_num_present_id').value;
	if(btn=='yes')
		window.open("content/bill"+acDraft+".pdf");
 }	
 	
function saveBill1(btn)
 {
	var acDraft = document.getElementById('ac_num_present1_id').value;
	if(btn=='yes')
		window.open("content/bill"+acDraft+".pdf");
 }	

	var storeStmt1 = null;
	
var statementWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Generate Statement',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acStat_id',
								name:'acStat',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								triggerAction:'all',
								typeAhead: true
			                },{
								xtype: 'datefield',
			                    fieldLabel: '<b>Start Date</b>',
			                    id: 'startdt',
								name:'startdt',
								width: 160,
								vtype: 'daterange',
								endDateField: 'enddt'
			                    },{
								xtype: 'datefield',
							    fieldLabel: '<b>End Date</b>',
							    name: 'enddt',
								width: 160,
							    id: 'enddt',
							    vtype: 'daterange',
							    startDateField: 'startdt' // id of the start date field
							      }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Generate',
						handler: function(){
								
								statementWin1.hide();
								var stmtRecord = Ext.data.Record.create([{name: 'tid'},{name: 'type'},{name: 'amount'},{name: 'date'},{name: 'time'},{name: 'balance'},{name: 'details'}]);

										var urlVar = "GenerateStatement?acStat="+document.getElementById('acStat_id').value+"&startdt="+document.getElementById('startdt').value+"&enddt="+document.getElementById('enddt').value;   
										    // create the data store
								storeStmt1 = new Ext.data.Store({
										        // load using HTTP
										        
												proxy: new Ext.data.HttpProxy({url: urlVar,method:'GET'}),		        
										        // the return will be XML, so lets set up a reader
										        reader: new Ext.data.JsonReader({
										               // records will have an "Item" tag
										               totalProperty: "size",    // The property which contains the total dataset size (optional)
													   root: "data",                // The property which contains an Array of row objects
													   id: "id"
										           },stmtRecord),
										           autoLoad: true
										    });
								 		

										    
										    // create the Grid
										    var gridStmt = new Ext.grid.GridPanel({
										        store: storeStmt1,
										        columns: [
										            {header: "Transaction ID", width: 80, sortable: true, dataIndex: 'tid'},
													{header: "Details", width: 225, sortable: true, dataIndex: 'details'},
										            {header: "Credit/Debit", width: 75, sortable: true, dataIndex: 'type'},
										            {header: "Amount", width: 80, sortable: true, dataIndex: 'amount'},
										            {header: "Date", width: 80, sortable: true, dataIndex: 'date'},
													{header: "Time", width: 80, sortable: true, dataIndex: 'time'},
													{header: "Balance", width: 80, sortable: true, dataIndex: 'balance'}
										        ],
										        stripeRows: true,
										        height:450,
										        width:700,
										        title:'Account Statement',
												renderTo:'gridStatement'
										    });
											gridStmt.getSelectionModel().selectFirstRow();

										   // window.print();
										   // window.open("content/"+acStatnum+".pdf");
										   Ext.MessageBox.show({
									           title:'Print Statement?',
									           msg: 'Do you want to print the statement or Save as PDF? Click Yes to Print, No to Save as PDF & Cancel to Exit',
									           buttons: Ext.MessageBox.YESNOCANCEL,
									           fn: doOperation,
									           icon: Ext.MessageBox.QUESTION
									       });
										   
						}
			        },{
			            text: 'Reset',
						handler : function(){
							statementWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        statementWin1.hide();
                    }
					}]
			    }) 


var statementWin1;				
function genStatement()
{
	if(!statementWin1){
            statementWin1 = new Ext.Window({
                applyTo     : 'quickArea3',
				title		: 'Account Statements',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: statementWin
            });
        }
        statementWin1.show('quickArea3');
}


var chLimitWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Draft Limit',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acnum_limit_id',
								name:'acnum_limit',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								triggerAction:'all',
								typeAhead: true
			                },{
								fieldLabel: '<b>New Limit</b>',
			                    id: 'limit_id',
								name:'limit'
			                    }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Change',
						handler: function(){
								var amt_limit = document.getElementById('limit_id').value;
								if(isNaN(amt_limit))
									Ext.MessageBox.alert('Validation Error','Amount should be Numeral');
								else if(amt_limit > 30000)
									Ext.MessageBox.alert('Range Error','You are not entitled to demand for more that Rs.30,000 for Draft Limit');
								else if(amt_limit < 0)
									Ext.MessageBox.alert('Range Error','Draft Limit cannot be negative');
								else if(amt_limit == '')
									Ext.MessageBox.alert('Validation Error','Amount cannot be blank');
								else if(document.getElementById('acnum_limit_id').value == 'Select Account')
									Ext.MessageBox.alert('Validation Error','Select an Account');
									else
									{
								chLimitWin.getForm().submit({
									method: 'POST',
									waitMsg: 'Operation in Progress...',
									url: 'ChangeLimit',
									success: function(){
										limitWin.hide();
										Ext.MessageBox.alert('Success','Limit Changed Successfully');
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Operation could not be completed... Try later or contact Administrator');
									}
								});
								}
						}
			        },{
			            text: 'Reset',
						handler : function(){
							chLimitWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        limitWin.hide();
                    }
					}]
			    }) 


				
				
var draftStatWin = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Draft Status',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acnum_limit1_id',
								name:'acnum_limit',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								triggerAction:'all',
								typeAhead: true
			                },{
								fieldLabel: '<b>Draft Number</b>',
			                    id: 'draftNum_id',
								name:'draftNum'
			                    }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Find',
						handler: function(){
								var DraftNumber = document.getElementById('draftNum_id').value;
								if(isNaN(DraftNumber))
									Ext.MessageBox.alert('Validation Error','Draft Number should be Numeral');
								else if(document.getElementById('acnum_limit1_id').value=='Select Account')
									Ext.MessageBox.alert('Validation Error','Select an Account');
								else if(DraftNumber=='')
									Ext.MessageBox.alert('Validation Error','Draft Number cannot be Blank');
								else
									{
								draftStatWin.getForm().submit({
									method: 'POST',
									waitMsg: 'Retrieving Status...',
									url: 'DraftStatus',
									success: function(response,request){
										draftStatusWin.hide();
										//var responseData = Ext.decode(response.responseText);
										Ext.MessageBox.alert('Draft Status','Your Draft Status is : '+request.result.data);
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Sorry! No such draft has been issued to your Account');
									}
								});
								}
						}
			        },{
			            text: 'Reset',
						handler : function(){
							draftStatWin.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        draftStatusWin.hide();
                    }
					}]
			    }) 

				

var draftStatusWin;
function showDraftStatus()
{
	if(!draftStatusWin){
            draftStatusWin = new Ext.Window({
                applyTo     : 'quickArea5',
				title		: 'Status Credentials',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: draftStatWin
            });
        }
        draftStatusWin.show('quickArea5');
}

var BillHistoryRecord = Ext.data.Record.create([
									{name: 'biller'},
									{name: 'tid'}
								]);
var bill_history_store = new Ext.data.Store({
									proxy: new Ext.data.HttpProxy({
										url: 'BillerHistory',
										method: 'POST'
									}),
									reader: new Ext.data.JsonReader({
										               // records will have an "Item" tag
										        totalProperty: "size",    // The property which contains the total dataset size (optional)
												root: "data",                // The property which contains an Array of row objects
												id: "id"},BillHistoryRecord),
									autoLoad: true
								});
var billHistoryItem = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'History Credentials',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Bill</b>',
			                    id: 'biller_history_id',
								name:'biller_history',
								store: bill_history_store,
								displayField: 'tid',
								valueField: 'tid',
								emptyText: 'Select Bill',
								triggerAction:'all',
								typeAhead: true
			                }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Print',
						handler: function(){
								if(document.getElementById('biller_history_id').value == 'Select Bill')
								{
									Ext.MessageBox.alert('Validation Error','Select one of your bills');
								}
								else
								{
								billHistoryWin.hide();
								billHistoryItem.getForm().submit({
									url: 'PrintBill',
									method: 'GET',
									waitMsg: 'Processing your request...',
									success: function(){
										var docNameBill = document.getElementById('biller_history_id').value;
										window.open("content/Bill"+docNameBill+".pdf");
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Could not generate Bill Receipt');
									}
								});								
											}
			        }},{
			            text: 'Reset',
						handler : function(){
							billHistoryItem.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        billHistoryWin.hide();
                    }
					}]
			    }); 







var billHistoryWin;
function billHistory()
{
	if(!billHistoryWin){
            billHistoryWin = new Ext.Window({
                applyTo     : 'quickArea8',
				title		: 'Bill History',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: billHistoryItem
            });
        }
        billHistoryWin.show('quickArea8');
}

var addBiller_store = new Ext.data.Store({
        // load using HTTP
        url: 'xml/billprovider.xml',

        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: 'Total'
           }, [
               {name:'title'}
           ])
    });
	
	 var resultTpl3 = new Ext.XTemplate(
        '<tpl for="."><div class="search-item3">',
            '<font size=2><b>{title}</b><br/></font>',
            '</div></tpl>'
    );





var addBillerItem = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Biller Details',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
								fieldLabel: '<b>Select Provider</b>',
								name: 'provider',
								id: 'provider_id',
								store: addBiller_store,
								displayField: 'title',
								typeAhead: false,
						        loadingText: 'Loading...',
						        pageSize:10,
						        hideTrigger:true,
						        tpl: resultTpl3,
								itemSelector: 'div.search-item3',
						        onSelect: function(record){ 
									document.getElementById('provider_id').value = record.data.title;
						        }
			                },
							{
								fieldLabel: '<b>Biller Nick</b>',
								name: 'title_biller',
								id: 'title_biller_id'
							}]
			            }]
			        },
			
			        buttons: [{
			            text: 'Add',
						handler: function(){
								if(document.getElementById('provider_id').value == '')
								{
									Ext.MessageBox.alert('Validation Error','Select one of Bill Providers');
								}
								else if(document.getElementById('title_biller_id').value == '')
								{
									Ext.MessageBox.alert('Validation Error','Select Nickname for your Biller');
								}
								else
								{
								addBillerWin.hide();
								addBillerItem.getForm().submit({
									url: 'AddBiller',
									method: 'GET',
									waitMsg: 'Processing your request...',
									success: function(){
										Ext.MessageBox.alert('Success','Biller created successfully');
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Biller could not be created. Try Again Later');
									}
								});								
											}
			        }},{
			            text: 'Reset',
						handler : function(){
							addBillerItem.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        addBillerWin.hide();
                    }
					}]
			    }) 


var addBillerWin;
function addBiller()
{
	if(!addBillerWin){
            addBillerWin = new Ext.Window({
                applyTo     : 'quickArea9',
				title		: 'Add Biller',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: addBillerItem
            });
        }
        addBillerWin.show('quickArea9');
}

var delBillerRecord = Ext.data.Record.create([
									{name: 'nick'}
								]);
var delBiller_store = new Ext.data.Store({
									proxy: new Ext.data.HttpProxy({
										url: 'SendBiller',
										method: 'GET'
									}),
									reader: new Ext.data.JsonReader({
										               // records will have an "Item" tag
										        totalProperty: "size",    // The property which contains the total dataset size (optional)
												root: "data",                // The property which contains an Array of row objects
												id: "id"},delBillerRecord),
									autoLoad: true
								});

var delBillerItem = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'Biller Details',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Biller Nick</b>',
			                    id: 'nick_biller_id',
								name:'nick_biller',
								store: delBiller_store,
								displayField: 'nick',
								valueField: 'nick',
								emptyText: 'Select Biller Nick',
								triggerAction:'all',
								typeAhead: false
			                }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Delete',
						handler: function(){
								if(document.getElementById('nick_biller_id').value == 'Select Biller Nick')
								{
									Ext.MessageBox.alert('Validation Error','Select one of the Biller');
								}
								else
								{
								delBillerWin.hide();
								delBillerItem.getForm().submit({
									url: 'DeleteBiller',
									method: 'GET',
									waitMsg: 'Processing your request...',
									success: function(){
										Ext.MessageBox.alert('Success','Biller deleted successfully');
									},
									failure: function(){
										Ext.MessageBox.alert('Failure','Biller could not be deleted. Try Again Later');
									}
								});								
											}
			        }},{
			            text: 'Reset',
						handler : function(){
							delBillerItem.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        delBillerWin.hide();
                    }
					}]
			    }) 


var delBillerWin;
function delBiller()
{
	if(!delBillerWin){
            delBillerWin = new Ext.Window({
                applyTo     : 'quickArea10',
				title		: 'Delete Biller',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: delBillerItem
            });
        }
        delBillerWin.show('quickArea10');
}




var historyForm = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'History Credentials',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acnum_history_id',
								name:'acnum_history',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								triggerAction:'all',
								typeAhead: true
			                }]
			            }]
			        },
			
			        buttons: [{
			            text: 'View',
						handler: function(){
								if(document.getElementById('acnum_history_id').value == 'Select Account')
								{
									Ext.MessageBox.alert('Validation Error','Select one of your accounts');
								}
								else
								{
								historyWin.hide();
								var DraftHistoryRecord = Ext.data.Record.create([
									{name: 'num'},
									{name: 'favour'},
									{name: 'amount'},
									{name: 'status'}
								]);
								var HistoryURL = "DraftHistory?acnum_history="+document.getElementById('acnum_history_id').value;
								var DraftHistoryStore = new Ext.data.Store({
									proxy: new Ext.data.HttpProxy({
										url: HistoryURL,
										method: 'GET'
									}),
									reader: new Ext.data.JsonReader({
										               // records will have an "Item" tag
										        totalProperty: "size",    // The property which contains the total dataset size (optional)
												root: "data",                // The property which contains an Array of row objects
												id: "id"},DraftHistoryRecord),
									autoLoad: true
								});
								var DraftHistoryGrid = new Ext.grid.GridPanel({
									store: DraftHistoryStore,
									 columns: [
										            {header: "Draft Number", width: 150, sortable: true, dataIndex: 'num'},
													{header: "In Favour Of", width: 250, sortable: true, dataIndex: 'favour'},
										            {header: "Amount", width: 150, sortable: true, dataIndex: 'amount'},
										            {header: "Status", width: 150, sortable: true, dataIndex: 'status'}
										        ],
									stripeRows: true,
									height: 450,
									width: 700,
									title: 'Draft History',
									renderTo: 'draftGrid'
								});
								DraftHistoryGrid.getSelectionModel().selectFirstRow();
									}
											}
			        },{
			            text: 'Reset',
						handler : function(){
							historyForm.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        historyWin.hide();
                    }
					}]
			    }) 





var historyWin;
function showDraftHistory()
{
	if(!historyWin){
            historyWin = new Ext.Window({
                applyTo     : 'quickArea6',
				title		: 'Draft History',
                layout      : 'fit',
                width       : 500,
                height      : 200,
                closeAction :'hide',
                plain       : true,
				items		: historyForm
            });
        }
        historyWin.show('quickArea6');
}


var TransactionForm = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'History Credentials',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'textfield',
			
			                items: [{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acnum_trans_history_id',
								name:'acnum_trans_history',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								triggerAction:'all',
								typeAhead: true
			                },{
								xtype: 'datefield',
			                    fieldLabel: '<b>Start Date</b>',
			                    id: 'startdt_trans',
								name:'startdt_trans',
								width: 160,
								vtype: 'daterange',
								endDateField: 'enddt_trans'
			                    },{
								xtype: 'datefield',
							    fieldLabel: '<b>End Date</b>',
							    name: 'enddt_trans',
								width: 160,
							    id: 'enddt_trans',
							    vtype: 'daterange',
							    startDateField: 'startdt_trans' // id of the start date field
							      }]
			            }]
			        },
			
			        buttons: [{
			            text: 'View',
						handler: function(){
						if(document.getElementById('acnum_trans_history_id').value == 'Select Account')
							Ext.MessageBox.alert('Validation Error','Select one of your accounts');
						else if(document.getElementById('startdt_trans').value == '')
							Ext.MessageBox.alert('Validation Error','Fill in the Starting Date');
						else if(document.getElementById('enddt_trans').value == '')
							Ext.MessageBox.alert('Validation Error','Fill in the Ending Date');
						else
						{
								TransactionWindow.hide();
								var TransactionHistoryRecord = Ext.data.Record.create([
									{name: 'to'},
									{name: 'tid'},
									{name: 'amount'},
									{name: 'date'}
								]);
								var TransactionURL = "TransactionHistory?acnum_trans_history="+document.getElementById('acnum_trans_history_id').value+"&startdt_trans="+document.getElementById('startdt_trans').value+"&enddt_trans="+document.getElementById('enddt_trans').value;
								var TransactionHistoryStore = new Ext.data.Store({
									proxy: new Ext.data.HttpProxy({
										url: TransactionURL,
										method: 'GET'
									}),
									reader: new Ext.data.JsonReader({
										               // records will have an "Item" tag
										        totalProperty: "size",    // The property which contains the total dataset size (optional)
												root: "data",                // The property which contains an Array of row objects
												id: "id"},TransactionHistoryRecord),
									autoLoad: true
								});
								var TransactionHistoryGrid = new Ext.grid.GridPanel({
									store: TransactionHistoryStore,
									 columns: [
										            {header: "Transferred To", width: 150, sortable: true, dataIndex: 'to'},
													{header: "Transaction ID", width: 250, sortable: true, dataIndex: 'tid'},
										            {header: "Amount", width: 150, sortable: true, dataIndex: 'amount'},
										            {header: "Date of Transfer", width: 150, sortable: true, dataIndex: 'date'}
										        ],
									stripeRows: true,
									height: 450,
									width: 700,
									title: 'Transaction History',
									renderTo: 'transaction'
								});
								}
											}
			        },{
			            text: 'Reset',
						handler : function(){
							TransactionForm.getForm().reset();
						}
			        },{
                    text     : 'Close',
                    handler  : function(){
                        TransactionWindow.hide();
                    }
					}]
			    }) 



var TransactionWindow;
function showTransactionHistory()
{
	if(!TransactionWindow){
            TransactionWindow = new Ext.Window({
                applyTo     : 'quickArea7',
				title		: 'Transaction History',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: TransactionForm
            });
        }
        TransactionWindow.show('quickArea7');
}





var limitWin;				
function chLimit()
{
	if(!limitWin){
            limitWin = new Ext.Window({
                applyTo     : 'quickArea4',
				title		: 'Change Draft Limit',
                layout      : 'fit',
                width       : 500,
                height      : 250,
                closeAction :'hide',
                plain       : true,
				items		: chLimitWin
            });
        }
        limitWin.show('quickArea4');
}


	
				

function conManager()
	{
		if(!conWindow){
            conWindow = new Ext.Window({
                applyTo     : 'quickArea2',
				title		: 'Control Manager',
                layout      : 'fit',
                width       : 500,
                height      : 150,
                closeAction :'hide',
                plain       : true,
				items		: controlWin
            });
        }
        conWindow.show('quickArea2');
	}


			

function chPass()
	{
		if(!passWindow){
            passWindow = new Ext.Window({
                applyTo     : 'quickArea',
				title		: 'Change Password',
                layout      : 'fit',
                width       : 500,
                height      : 220,
                closeAction :'hide',
                plain       : true,
				items		: chPassWin
            });
        }
        passWindow.show('quickArea');
	}
	
var favWindow;
function manFavour()
{
	if(!favWindow){
            favWindow = new Ext.Window({
                applyTo     : 'quickArea1',
				title		: 'Manage Favorites',
                layout      : 'fit',
                width       : 500,
                height      : 270,
                closeAction :'hide',
                plain       : true,
				items		: manFavWin
            });
        }
        favWindow.show('quickArea1');
}



Ext.onReady(function(){
Ext.QuickTips.init();
var quickToolBar = new Ext.Toolbar();
quickToolBar.height = 25;
quickToolBar.width = 956;
quickToolBar.render('quickBar');

var favStore = new Ext.data.SimpleStore({
                            fields: ['fav']
                        });

quickToolBar.add(new Ext.form.TextField({
			id:'j2box',
			width: 160,
			value:'Jump Command',
			name:'j2box'
}),'-',new Ext.form.Label({
			id:'newsLabel',
			html:'<marquee><b>*** NEWS UPDATES ***   COMING SOON ! KEEP WATCHING !</b></marquee>',
			width: 790			
}),'-'		
,new Ext.Toolbar.Spacer(),{	
            text:'Logout',
			id: 'Logout',
			pressed: true,
			tooltip: 
				{
					text:'Click to Log-off your Account',
					title: 'LogOff'
				},
			handler: function() {
				window.location = "Logout";
			}
});

var responseData;

var fs = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 85,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
       listeners:{
        beforeRender : function(){
        
       
        	Ext.Ajax.request({
        		method: 'POST',
        		url:'MyProfile',
        		success: function(response,request)
        		{
        			responseData = Ext.decode(response.responseText);
//        			Response: A JSON String of the format:"{success: true/false,name: '...' , address: '...', mail: '.....', phone:'....', user: 'UserID', llt: 'last login time(in string form)', lld:'last login date(in string form)', theme:'....', security:'Enabled/Disabled'}"
					document.getElementById('fullName_id').value = responseData.name;
					document.getElementById('fullAddress_id').value = responseData.address;
					document.getElementById('userMail_id').value = responseData.mail;
					document.getElementById('userPhone_id').value = responseData.phone;
					document.getElementById('userId_id').value = responseData.user;
					document.getElementById('llTime_id').value = responseData.llt;
					document.getElementById('llDate_id').value = responseData.lld;
					document.getElementById('currentTheme_id').value = responseData.theme;
					document.getElementById('currentSecurity_id').value = responseData.security;
        		},
        		failure: function()
        		{
        			Ext.MessageBox.alert('Connection Failure','Could not load your Profile Data. Please re-login to the system');
        			window.location("index.jsp");
        		}        		
        	});
        
        
        
        }},
        items: [{
            layout: 'column',
            border: false,
            defaults: {
                columnWidth: '.5',
                border: false
            },
            
            /*====================================================================
             * Individual checkbox/radio examples
             *====================================================================*/
            
            // Using checkbox/radio groups will generally be easier and more flexible than
            // using individual checkbox and radio controls, but this shows that you can
            // certainly do so if you only need a single control, or if you want to control  
            // exactly where each check/radio goes within your layout.
            
            items: [{
                bodyStyle: 'padding-right:5px;',
                items:         {
                    xtype:'fieldset',
                    title: 'Personal Details',
                    autoHeight: true,
					collapsible: true,
                    defaultType: 'textfield',  // each item will be a checkbox
					defaults: {width: 220},
                    items: [{
                        name: 'fullName',
                        fieldLabel: 'Display Name',
						disabled: true,
						id: 'fullName_id'
                    },{
                        fieldLabel: 'Address',
                        name: 'fullAddress',
						disabled: true,
						id: 'fullAddress_id'
                    },{
                        fieldLabel: 'E-mail',
						name: 'userMail',
						id: 'userMail_id'
                    },{
                        fieldLabel: 'Phone',
						name: 'userPhone',
						id: 'userPhone_id'
                    }]
                }
            },{
                bodyStyle: 'padding-left:5px;',
                items: {
                    xtype:'fieldset',
                    title: 'Login Information',
                    autoHeight: true,
					collapsible: true,
                    defaultType: 'textfield',  // each item will be a radio button
					defaults: {width: 220},
                    items: [{
                        name: 'userId',
                        fieldLabel: 'UserID',
						disabled: true,
						id: 'userId_id'
                    },{
                        fieldLabel: 'Last Login Time',
                        name: 'llTime',
						disabled: true,
						id: 'llTime_id'
                    },{
                        fieldLabel: 'Last Login Date',
                        name: 'llDate',
						disabled: true,
						id: 'llDate_id'
                    }]
                }
            }]
        },
		{
					xtype:'fieldset',
                    title: 'Display Theme',
                    autoHeight: true,
					collapsible: true,
                    defaultType: 'radio',
					labelWidth: 90,
                    items: [{
						xtype: 'textfield',
                        name: 'currentTheme',
						fieldLabel: 'Current Theme',
						disabled: true,
						id: 'currentTheme_id'
                    },
					{
						xtype:'radiogroup',
						name: 'newTheme',
                        fieldLabel: 'Change Theme',
						items:[
							{boxLabel: 'Theme 1',name:'theme', inputValue:1,checked: true},
							{boxLabel: 'Theme 2',name:'theme', inputValue:2},
							{boxLabel: 'Theme 3',name:'theme', inputValue:3}
						]
                    }]
		},
		{
					xtype:'fieldset',
                    title: 'Security Zone',
                    autoHeight: true,
					collapsible: true,
                    defaultType: 'radio',
					labelWidth: 90,
                    items: [{
						xtype: 'textfield',
                        name: 'currentSecurity',
                        fieldLabel: 'Security Option',
						disabled: true,
						id: 'currentSecurity_id'
                    },
					{
						xtype:'radiogroup',
						name: 'newSecurity',
                        fieldLabel: '',
						labelSeparator:'',
						items:[
							{boxLabel: 'Enable',name:'securityChoice', inputValue:1, checked:true},
							{boxLabel: 'Disable',name:'securityChoice', inputValue:2}
						]
                    }]
		}
        ]
    });

 fs.addButton('Save', function(){
        //Save Button
		if(document.getElementById('userMail_id').value=='')
			Ext.MessageBox.alert('Validation Error','E-mail cannot be left blank');
		else if(document.getElementById('userPhone_id').value=='')
			Ext.MessageBox.alert('Validation Error','Phone Number cannot be left blank');
		else
		{
        fs.getForm().submit({
			url: 'SaveProfile',
			method: 'POST',
			waitMsg: 'Saving your Profile Details...',
			success: function(){
				Ext.MessageBox.alert('Success','Profile Updated Successfully. Changes shall be reflected the next time you Login');				
			},
			failure: function(){
				Ext.MessageBox.alert('Failure','Could not Save Profile Details. Please try again later.');
			}
		});
		
		}
		
    });

	
var commonCombo = new Ext.data.Store({
        // load using HTTP
        proxy: new Ext.data.HttpProxy({url: 'SendAccount',method:'GET'}),
        // the return will be XML, so lets set up a reader
        reader: new Ext.data.JsonReader({
               // records will have an "Item" tag
               totalProperty: "size",    // The property which contains the total dataset size (optional)
			   root: "data",                // The property which contains an Array of row objects
			   id: "id"
           },[{name: 'acnum'}]),
        autoLoad: true
    });

	
var j2box_store = new Ext.data.Store({
        // load using HTTP
        url: 'xml/commands.xml',

        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: 'Total'
           }, [
               {name:'title'},
			   {name:'desc'}
           ])
    });
	
	 var resultTpl = new Ext.XTemplate(
        '<tpl for="."><div class="search-item">',
            '<font size=1><b>{title}</b><br/></font>',
            '<font size=1>{desc}</font>',
        '</div></tpl>'
    );

	
var j2Box_search = new Ext.form.ComboBox({
        store: j2box_store,
        displayField:'title',
        typeAhead: false,
        loadingText: 'Loading...',
        width: 160,
        pageSize:10,
        hideTrigger:true,
        tpl: resultTpl,
        applyTo: 'j2box',
        itemSelector: 'div.search-item',
        onSelect: function(record){
		// override default onSelect to do redirect
			document.getElementById('j2box').value = record.data.title;
            if(record.data.title=='Add Biller')
				addBiller();
			if(record.data.title=='Bill History')
				billHistory();
			if(record.data.title=='Change Draft Limit')
				chLimit();
			if(record.data.title=='Change Password')
				chPass();
			if(record.data.title=='Control Manager')
				conManager();
			if(record.data.title=='Delete Biller')
				delBiller();
			if(record.data.title=='Draft Status')
				showDraftStatus();
        }
    });
	
var dd_store = new Ext.data.Store({
        // load using HTTP
        url: 'xml/payablelist.xml',

        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: 'Total'
           }, [
               {name:'title'},
			   {name:'code'}
           ])
    });
	
	 var resultTpl1 = new Ext.XTemplate(
        '<tpl for="."><div class="search-item1">',
            '<font size=2><b>{title}</b><br/></font>',
            '<font size=1>Bank Code: {code}</font>',
        '</div></tpl>'
    );

	
var dd = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Draft Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
				fieldLabel: '<b>Pay To</b>',
				name: 'payTo',
				id: 'payTo_id'
			},{
				fieldLabel: '<b>Amount</b>',
				name: 'amount_dd',
				id: 'amount_dd_id'
			},{
				xtype: 'combo',
				fieldLabel: '<b>Payable At</b>',
				name: 'payAt',
				id: 'payAt_id',
				store: dd_store,
				displayField: 'title',
				typeAhead: false,
		        loadingText: 'Loading...',
		        pageSize:10,
		        hideTrigger:true,
		        tpl: resultTpl1,
				itemSelector: 'div.search-item1',
		        onSelect: function(record){ 
					document.getElementById('payAt_id').value = record.data.title;
		        }
			},{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'account_id',
								name:'account',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								typeAhead: true,
								triggerAction:'all'
			                },{
				fieldLabel: '<b>Transaction Password</b>',
				name: 'trans_pass',
				id: 'trans_pass_id',
				inputType: 'password'
			},{
				xtype: 'radiogroup',
				fieldLabel: '<b>Collection Mode</b>',
				items: [
				{boxLabel: 'Courier', name: 'mode', inputValue: 1,checked: true},
				{boxLabel: 'In Person', name: 'mode', inputValue: 2}
				]
			}]
	   }
	   });
	
dd.addButton('Issue',function(){

	if(isNaN(document.getElementById('amount_dd_id').value))
		Ext.MessageBox.alert('Validation Error','Amount should be Numeral');
	else if(document.getElementById('amount_dd_id').value == '')
		Ext.MessageBox.alert('Validation Error','Amount should be Filled');
	else if(document.getElementById('payTo_id').value == '')
		Ext.MessageBox.alert('Validation Error','Pay To is a mandatory Field');
	else if(document.getElementById('payAt_id').value == '')
		Ext.MessageBox.alert('Validation Error','Payable At is a mandatory Field');
	else if(document.getElementById('account_id').value == 'Select Account')
		Ext.MessageBox.alert('Validation Error','Select One Account');
	else if(document.getElementById('trans_pass_id').value == '')
		Ext.MessageBox.alert('Validation Error','Transaction Password is a required Field');
	else{
	dd.getForm().submit({
		url: 'DraftIssue',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(request,response){
				Ext.MessageBox.confirm('Success', 'Your Draft has been issued. Click Yes to Save as PDF and No to Cancel', saveDraft);
			},
			failure: function(request,response){
				Ext.MessageBox.alert('Failure','Could not Issue Draft to you due to the following reason: '+response.result.data);
			}
	});
	}
});	
	
dd.addButton('Reset',function(){
	dd.getForm().reset();
});	

var a2a = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Transfer Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype: 'combo',
			                    fieldLabel: '<b>Source Account</b>',
			                    id: 'source_id',
								name:'source',
								store: storeStmt,
								displayField: 'acnum',
								emptyText: 'Select Account',
								typeAhead: false,
								triggerAction:'all'
			                },{
				fieldLabel: '<b>Destination Account</b>',
				name: 'destination',
				id: 'destination_id'
			},{
				fieldLabel: '<b>Amount</b>',
				name: 'amount_a2a',
				id: 'amount_a2a_id'
			},{
				fieldLabel: '<b>Transaction Password</b>',
				name: 'trans_pass_a2a',
				id: 'trans_pass_a2a_id',
				inputType: 'password'
			}]
	   }
	   });
	
a2a.addButton('Transfer',function(){
	if(document.getElementById('source_id').value == 'Select Account')
		Ext.MessageBox.alert('Validation Error', 'Select One of your Accounts');
	else if(document.getElementById('destination_id').value == '')
		Ext.MessageBox.alert('Validation Error', 'Enter the value of Destination Account');
	else if(isNaN(document.getElementById('destination_id').value))
		Ext.MessageBox.alert('Validation Error', 'Destination Account Number should be numeral');
	else if(isNaN(document.getElementById('amount_a2a_id').value))
		Ext.MessageBox.alert('Validation Error', 'Amount should be numeral');
	else if(document.getElementById('amount_a2a_id').value <= 100 )
		Ext.MessageBox.alert('Validation Error', 'Amount should be more than Rs.100');
	else if(document.getElementById('amount_a2a_id').value == '')
		Ext.MessageBox.alert('Validation Error', 'Enter the amount to be transferred');
	else if(document.getElementById('trans_pass_a2a_id').value == '')
		Ext.MessageBox.alert('Validation Error', 'Enter the Transaction Password');
	else
	{
	a2a.getForm().submit({
		url: 'A2A',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(request,response){
				Ext.MessageBox.alert('Success', 'Amount transferred successfully. The transaction id is : '+ response.result.data);
			},
			failure: function(request,response){
				Ext.MessageBox.alert('Failure','Could not Issue Draft to you due to the following reason: '+response.result.data);
			}
	});
	}
});	
	
a2a.addButton('Reset',function(){
	a2a.getForm().reset();
});	

var inter_store = new Ext.data.Store({
        // load using HTTP
        url: 'xml/interbank.xml',

        // the return will be XML, so lets set up a reader
        reader: new Ext.data.XmlReader({
               // records will have an "Item" tag
               record: 'Item',
               id: 'id',
               totalRecords: 'Total'
           }, [
               {name:'title'}
           ])
    });
	
	 var resultTpl2 = new Ext.XTemplate(
        '<tpl for="."><div class="search-item2">',
            '<font size=2><b>{title}</b><br/></font>',
        '</div></tpl>'
    );



var inter = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Transfer Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype: 'combo',
			                    fieldLabel: '<b>Source Account</b>',
			                    id: 'src_bank_ac_id',
								name:'src_bank_ac',
								store: commonCombo,
								displayField: 'acnum',
								emptyText: 'Select Account',
								typeAhead: false,
								triggerAction:'all'
			                },{
								xtype: 'combo',
			                    fieldLabel: '<b>Destination Bank</b>',
			                    id: 'dest_bank_id',
								name:'dest_bank',
								typeAhead: false,
								store: inter_store,
								displayField: 'title',
								loadingText: 'Loading...',
						        pageSize:10,
						        hideTrigger:true,
						        tpl: resultTpl2,
								itemSelector: 'div.search-item2',
						        onSelect: function(record){ 
									document.getElementById('dest_bank_id').value = record.data.title;
						        }
			                },{
				fieldLabel: '<b>Destination Credentials</b>',
				name: 'dest_bank_ac',
				id: 'dest_bank_ac_id'
			},{
				fieldLabel: '<b>Amount</b>',
				name: 'inter_amount',
				id: 'inter_amount_id'
			},{
				fieldLabel: '<b>Transaction Password</b>',
				name: 'trans_pass_inter',
				id: 'trans_pass_inter_id',
				inputType: 'password'
			}]
	   }
	   });
	
inter.addButton('Transfer',function(){
	if(document.getElementById('src_bank_ac_id').value=='Select Account')
		Ext.MessageBox.alert('Validation Error', 'Select an Account');
	else if(document.getElementById('dest_bank_id').value=='')
		Ext.MessageBox.alert('Validation Error', 'Select Destination Bank');
	else if(document.getElementById('dest_bank_ac_id').value=='')
		Ext.MessageBox.alert('Validation Error', 'Select Destination Bank Account');
	else if(isNaN(document.getElementById('dest_bank_ac_id').value))
		Ext.MessageBox.alert('Validation Error', 'Destination Account Number should be numeral');
	else if(document.getElementById('inter_amount_id').value=='')
		Ext.MessageBox.alert('Validation Error', 'Enter amount to be transferred');
	else if(isNaN(document.getElementById('inter_amount_id').value))
		Ext.MessageBox.alert('Validation Error', 'Amount should be numeral');
	else if(document.getElementById('trans_pass_inter_id').value=='')
		Ext.MessageBox.alert('Validation Error', 'Transaction Password cannot be blank');
	else
	{
	inter.getForm().submit({
		url: 'InterBanking',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(request,response){
				Ext.MessageBox.alert('Success', 'Amount transferred successfully. The transaction id is : '+ response.result.data);
			},
			failure: function(request,response){
				Ext.MessageBox.alert('Failure','Could not Issue Draft to you due to the following reason: '+response.result.data);
			}
	});
	}
})
;	
	
inter.addButton('Reset',function(){
	inter.getForm().reset();
});	

var pbills = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Bill Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Biller</b>',
			                    id: 'biller_nick_present_id',
								name:'biller_nick_present',
								store: delBiller_store,
								displayField: 'nick',
								valueField: 'nick',
								emptyText: 'Select Biller',
								typeAhead: false,
								triggerAction:'all',
								listeners:{
									'select' : function(){
										var billnumber = document.getElementById('bill_num_present_id');
										var billernick = document.getElementById('biller_nick_present_id');
										var billamount = document.getElementById('amount_present_id');
										var billacc = document.getElementById('ac_num_present_id');
										var transpass = document.getElementById('pass_present_id');
										billnumber.disabled = false;
										billamount.disabled = false;
										billacc.disabled = false;
										transpass.disabled = false;
										Ext.Ajax.request({
										method: 'GET',
										url: 'PresentmentBills?biller_nick_present='+billernick.value,
										success: function(response,request){
											var favData = Ext.decode(response.responseText);
											billnumber.value = ""+favData.num;
											billamount.value = ""+favData.amt;
										},
										failure: function(){
											Ext.MessageBox.alert('Failure','Could not Load Data. Try later');
										}
									});
									}
								}
			                },{
				fieldLabel: '<b>Bill Number</b>',
				name: 'bill_num_present',
				id: 'bill_num_present_id',
				disabled: true
			},{
				fieldLabel: '<b>Bill Amount</b>',
				name: 'amount_present',
				id: 'amount_present_id',
				disabled: true
			},{
								xtype: 'combo',
			                    fieldLabel: '<b>Pay through Account</b>',
			                    id: 'ac_num_present_id',
								name:'ac_num_present',
								emptyText: 'Select Account',
								typeAhead: false,
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								disable: true,
								triggerAction:'all'
			                },{
				fieldLabel: '<b>Transaction Password</b>',
				name: 'pass_present',
				id: 'pass_present_id',
				inputType: 'password',
				disabled: true
			}]
	   }
	   });
	
pbills.addButton('Pay',function(){
	if(document.getElementById('biller_nick_present_id').value=='Select Biller')
		Ext.MessageBox.alert('Validation Error', 'Select a Biller');
	else if(document.getElementById('ac_num_present_id').value=='Select Account')
		Ext.MessageBox.alert('Validation Error', 'Select an Account');
	else if(isNaN(document.getElementById('ac_num_present_id').value))
		Ext.MessageBox.alert('Validation Error', 'Account Number should be numeral');
	else if(document.getElementById('pass_present_id').value=='')
		Ext.MessageBox.alert('Validation Error', 'Enter your Transaction Password');
	else
	{
		pbills.getForm().submit({
		url: 'PresentmentPay',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(){
				Ext.MessageBox.confirm('Success', 'Your Bill has been paid. Click Yes to Save Receipt as PDF and No to Cancel', saveBill);
			},
			failure: function(){
				Ext.MessageBox.alert('Failure','Could not pay your Bill');
			}
		});
	}
});	
	
pbills.addButton('Reset',function(){
	pbills.getForm().reset();
});	

var pbills1 = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Bill Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype: 'combo',
								fieldLabel: '<b>Select Provider</b>',
								name: 'nick_present1',
								id: 'nick_present1_id',
								store: delBiller_store,
								displayField: 'nick',
								valueField: 'nick'
			                },{
				fieldLabel: '<b>Bill Number</b>',
				name: 'bill_num_present1',
				id: 'bill_num_present1_id'
			},{
				fieldLabel: '<b>Bill Amount</b>',
				name: 'amount_present1',
				id: 'amount_present1_id'
			},{
								xtype: 'combo',
			                    fieldLabel: '<b>Pay through Account</b>',
			                    id: 'ac_num_present1_id',
								name:'ac_num_present1',
								emptyText: 'Select Account',
								typeAhead: false,
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								triggerAction:'all'
			                },{
				fieldLabel: '<b>Transaction Password</b>',
				name: 'pass_present1',
				id: 'pass_present1_id',
				inputType: 'password'
			}]
	   }
	   });
	
pbills1.addButton('Pay',function(){
	if(document.getElementById('nick_present1_id').value == '')
		Ext.MessageBox.alert('Validation Error','Select a provider');
	else if(document.getElementById('bill_num_present1_id').value == '')
		Ext.MessageBox.alert('Validation Error','Enter the Bill Number');
	else if(isNaN(document.getElementById('bill_num_present1_id').value))
		Ext.MessageBox.alert('Validation Error','Bill Number must be numeral');
	else if(document.getElementById('amount_present1_id').value == '')
		Ext.MessageBox.alert('Validation Error','Enter the Bill Amount');
	else if(isNaN(document.getElementById('amount_present1_id').value))
		Ext.MessageBox.alert('Validation Error','Bill Amount must be numeral');
	else if(document.getElementById('ac_num_present1_id').value == 'Select Account')
		Ext.MessageBox.alert('Validation Error','Select an Account');
	else if(isNaN(document.getElementById('ac_num_present1_id').value))
		Ext.MessageBox.alert('Validation Error','Account Number must be numeral');
	else if(document.getElementById('pass_present1_id').value == '')
		Ext.MessageBox.alert('Validation Error','Transaction Password is a required field');
	else
	{
		pbills1.getForm().submit({
		url: 'PaymentBill',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(){
				Ext.MessageBox.confirm('Success', 'Your Bill has been paid. Click Yes to Save Receipt as PDF and No to Cancel', saveBill1);
			},
			failure: function(){
				Ext.MessageBox.alert('Failure','Could not pay your Bill');
			}
		});
	}
});	
	
pbills1.addButton('Reset',function(){
	pbills.getForm().reset();
});	



var chequeBook = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Cheque Book Details',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype: 'combo',
			                    fieldLabel: '<b>Select Account</b>',
			                    id: 'acCheque_id',
								name:'acCheque',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								typeAhead: false,
								triggerAction:'all'
			                },{
								xtype:'radiogroup',
								fieldLabel: '<b>Quantity</b>',
								items:[
									{boxLabel: '10',name:'qtyCheque', inputValue:10,checked: true},
									{boxLabel: '25',name:'qtyCheque', inputValue:25},
									{boxLabel: '50',name:'qtyCheque', inputValue:50},
									{boxLabel: '100',name:'qtyCheque', inputValue:100}
									]
			                },{
								xtype:'radiogroup',
								fieldLabel: '<b>Mode</b>',
								items:[
									{boxLabel: 'Courier',name:'modeCheque', inputValue:'Courier',checked: true},
									{boxLabel: 'In Person',name:'modeCheque', inputValue:'Self'}	
									]									
			                }]
	   }
	   });
	
chequeBook.addButton('Issue',function(){
	if(document.getElementById('acCheque_id').value=='Select Account')
		Ext.MessageBox.alert('Validation Error','Select an Account');
	else if(isNaN(document.getElementById('acCheque_id').value))
		Ext.MessageBox.alert('Validation Error','Account Number should be Numeral');
	else
	{
		chequeBook.getForm().submit({
		url: 'ChequeBookIssue',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(request,response){
				Ext.MessageBox.alert('Success', 'Your ChequeBook numbered '+ response.result.data+ ' has been issued. Please collect through Courier/Branch');
			},
			failure: function(){
				Ext.MessageBox.alert('Failure','Could not issue ChequeBook to you');
			}
		});
	}
});	
	
chequeBook.addButton('Reset',function(){
	chequeBook.getForm().reset();
});	


var SI = new Ext.FormPanel({
        frame: true,
        title:'',
        labelAlign: 'right',
        labelWidth: 185,
        width:720,
        waitMsgTarget: true,
       autoScroll: true,
	   items: {
			xtype: 'fieldset',
			title: 'Standing Instruction Credentials',
            autoHeight: true,
			collapsible: true,
            defaultType: 'textfield',  // each item will be a checkbox
			defaults: {width: 220},
			items:[{
								xtype:'radiogroup',
								fieldLabel: '<b>Instruction type</b>',
								items:[
									{boxLabel: 'Loan Transfer',name:'type_inst', inputValue:'Loan Transfer',checked: true},
									{boxLabel: 'Account Transfer',name:'type_inst', inputValue:'Account Transfer'}
									]
			                },{
								xtype: 'combo',
			                    fieldLabel: '<b>Source Account</b>',
			                    id: 'account_from_inst_id',
								name:'account_from_inst',
								store: storeStmt,
								displayField: 'acnum',
								valueField: 'acnum',
								emptyText: 'Select Account',
								typeAhead: false,
								triggerAction:'all'
			                },{
								fieldLabel: '<b>Destination Account</b>',
								name: 'account_to_inst',
								id: 'account_to_inst_id'
							},{
								fieldLabel: '<b>Amount</b>',
								name: 'amount_instr',
								id: 'amount_instr_id'
							},{
								fieldLabel: '<b>Date</b>',
								name: 'date_instr',
								id: 'date_instr_id'
							},{
								xtype:'radiogroup',
								fieldLabel: '<b>Frequency(months)</b>',
								items:[
									{boxLabel: '1',name:'time_instr', inputValue:'Monthly',checked: true},
									{boxLabel: '3',name:'time_instr', inputValue:'Quarterly'},
									{boxLabel: '6',name:'time_instr', inputValue:'Half-Yearly'},
									{boxLabel: '12',name:'time_instr', inputValue:'Yearly'}
									]									
			                }]
	   }
	   });
	
SI.addButton('Issue',function(){
	if(document.getElementById('account_from_inst_id').value=='Select Account')
		Ext.MessageBox.alert('Validation Error','Select a Source Account');
	else if(isNaN(document.getElementById('account_from_inst_id').value))
		Ext.MessageBox.alert('Validation Error','Source Account Number should be Numeral');
	else if(document.getElementById('account_to_inst_id').value=='')
		Ext.MessageBox.alert('Validation Error','Destination Account cannot be blank');
	else if(isNaN(document.getElementById('account_to_inst_id').value))
		Ext.MessageBox.alert('Validation Error','Destination Account Number should be Numeral');
	else if(document.getElementById('amount_instr_id').value=='')
		Ext.MessageBox.alert('Validation Error','Amount cannot be blank');
	else if(isNaN(document.getElementById('amount_instr_id').value))
		Ext.MessageBox.alert('Validation Error','Amount should be Numeral');
	else if(document.getElementById('date_instr_id').value=='')
		Ext.MessageBox.alert('Validation Error','Date cannot be blank');
	else if(isNaN(document.getElementById('date_instr_id').value))
		Ext.MessageBox.alert('Validation Error','Date should be Numeral');
	else
	{
		SI.getForm().submit({
		url: 'StandingInstruction',
			method: 'POST',
			waitMsg: 'Processing your request...',
			success: function(request,response){
				Ext.MessageBox.alert('Success', 'Your Standing Instruction has been successfully issued to the IBS System');
			},
			failure: function(){
				Ext.MessageBox.alert('Failure','Could not issue your Standing Instruction');
			}
		});
	}
});	
	
SI.addButton('Reset',function(){
	SI.getForm().reset();
});	


	
	
	
        // tabs for the center
        var tabs = new Ext.TabPanel({
            region    : 'center',
            margins   : '3 3 3 0', 
            activeTab : 0,
			collapsible: true,
			autoScroll: true,
			defaults  : {
				autoScroll : true
			},
			items     : [{
                title    : '<B>My Banking</B>',
				xtype:'tabpanel',
				activeTab:0,
				items:[{
					title: '<b>My Profile</b>',
					items: fs
				},{
					title: '<b>My Accounts</b>',
					xtype: 'tabpanel',
					activetab:0,
					items:[{
						title: '<b>Account Summary</b>',
						contentEl: 'grid'						
					},{
						title: '<b>Account Statements</b>',
						contentEl: 'gridStatement'						
					}],
					listeners: {
					'tabchange': function(tabPanel, tab){
						if(tab.title == "<b>Account Summary</b>")
						{
							var accRecord = Ext.data.Record.create([
						    {name: 'acnum'},     // "mapping" property not needed if it's the same as "name"
						    {name: 'actype'},
							{name: 'holder'},
							{name: 'balance'}
							]);
						
						   
						    // create the data store
						    var store = new Ext.data.Store({
						        // load using HTTP
						        url: 'AccountSummary',
						        // the return will be XML, so lets set up a reader
						        reader: new Ext.data.JsonReader({
						               // records will have an "Item" tag
						               totalProperty: "size",    // The property which contains the total dataset size (optional)
									   root: "data",                // The property which contains an Array of row objects
									   id: "id"
						           },accRecord)
						    });
						
						    store.load();
						    // create the Grid
						    var grid = new Ext.grid.GridPanel({
						        store: store,
						        columns: [
						            {id:'acNum',header: "Account Number", width: 175, sortable: true, dataIndex: 'acnum'},
						            {header: "Account Type", width: 150, sortable: true, dataIndex: 'actype'},
						            {header: "Holder (any one)", width: 200, sortable: true, dataIndex: 'holder'},
						            {header: "Balance", width: 175, sortable: true, dataIndex: 'balance'}
						        ],
						        stripeRows: true,
						        height:450,
						        width:700,
						        title:'Account Summary',
								renderTo:'grid'
						    });

	
						}
						if(tab.title == "<b>Account Statements</b>")
						{
							genStatement();
						}
					}					
				}}
				],
				listeners: {
					'tabchange': function(tabPanel, tab){
						if(tab.title == "<b>My Accounts</b>")
						{
							document.getElementById('west').innerHTML = "";
						}
						if(tab.title == "<b>My Profile</b>")
						{
							var link4 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"chPass()\"><b>Change Password</b></a>";
							document.getElementById('west').innerHTML = link4;
							//var link5 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"manFavour()\"><b>Manage Favourites</b></a>";
							//document.getElementById('west').innerHTML += link5;
							var link6 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"conManager()\"><b>Control Manager</b></a>";
							document.getElementById('west').innerHTML += link6;
						}
                }

				}
             },{
                title    : '<b>Remittance</b>',
				xtype:'tabpanel',
				activeTab:0,
				layoutOnTabChange: true,
				items:[{
					title: '<b>Demand Draft</b>',
					items: dd
				},{
					title: '<b>Draft History</b>',
					contentEl: 'draftGrid'
				},{
					title: '<b>IntraBanking</b>',
					items: a2a
				},{
					title: '<b>InterBanking</b>',
					items: inter
				},{
					title: '<b>Transaction History</b>',
					contentEl: 'transaction'
				}				
				],
				listeners: {
					'tabchange': function(tabPanel, tab){
						if(tab.title == "<b>Demand Draft</b>")
						{
							var link2 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"chLimit()\"><b>Change Limit</b></a>";
							document.getElementById('west').innerHTML = link2;
							var link4 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"showDraftStatus()\"><b>Draft Status</b></a>";
							document.getElementById('west').innerHTML += link4;
						}
						if(tab.title == "<b>Draft History</b>")
						{
							document.getElementById('west').innerHTML = "";
							showDraftHistory();
						}
						if(tab.title == "<b>IntraBanking</b>")
						{
							document.getElementById('west').innerHTML = "";
						}
						if(tab.title == "<b>InterBanking</b>")
						{
							document.getElementById('west').innerHTML = "";
						}
						if(tab.title == "<b>Transaction History</b>")
						{
							document.getElementById('west').innerHTML = "";
							showTransactionHistory();
						}
                }

				}
             },{ 
                title    : '<b>Bill Services</b>',
				xtype:'tabpanel',
				activeTab:0,
				layoutOnTabChange: true,
				items:[{
					title: '<b>Presentment Bills</b>',
					items: pbills
				},{
					title: '<b>Payment Bills</b>',
					items: pbills1
				}
				]
            },{ 
                title    : '<b>Requests</b>',
				xtype:'tabpanel',
				activeTab:0,
				layoutOnTabChange: true,
				items:[{
					title: '<b>Cheque Book</b>',
					items: chequeBook
				},{
					title: '<b>Standing Instructions</b>',
					items: SI
				}
				]
            },{ 
                title    : '<b>Help</b>'
            }],
			listeners:{
				'tabchange': function(tabPanel, tab){
					if(tab.title == "<b>Bill Services</b>")
					{
							var link1 = "<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"billHistory()\"><b>History</b></a>";
							document.getElementById('west').innerHTML = link1;
							var link2 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"addBiller()\"><b>Add Biller</b></a>";
							document.getElementById('west').innerHTML += link2;
							var link3 = "<br/><br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--<a href=\"#\" onClick=\"delBiller()\"><b>Delete Biller</b></a>";
							document.getElementById('west').innerHTML += link3;
					}
					if(tab.title == "<b>Requests</b>")
					{
						document.getElementById('west').innerHTML = "";
					}
					if(tab.title == "<b>Help</b>")
					{
						document.getElementById('west').innerHTML = "";
					}
				}
			}
        });

		var subtabs = new Ext.TabPanel({
            region    : 'center',
            margins   : '3 3 3 0', 
            activeTab : 0,
			collapsible: true,
			autoScroll: true,
			defaults  : {
				autoScroll : true
			},
			items     : [{
                title    : '<B>My Accounts</B>'
             },{
                title    : '<b>My Profile</b>'
             }]
        });
		
        // Panel for the west
        var nav = new Ext.Panel({
            title       : 'Quick Links',
            region      : 'west',
            split       : true,
            width       : 200,
            collapsible : true,
            margins     : '3 0 3 3',
            cmargins    : '3 3 3 3',
			contentEl	: 'west'
        }); 

		
        var win = new Ext.Window({
            title    : 'My Workspace Area',
            closable : false,
            width    : 950,
            height   : 500,
            //border : false,
            plain    : true,
            layout   : 'border',
			draggable : false,
            items    : [nav, tabs]
        });

		win.setPagePosition(2,122);
		win.show();
      //  win.render('container');
		/*if(tabs.getActiveTab().title == "My Banking")
		{
			
		}
*/
}
);