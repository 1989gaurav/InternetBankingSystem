Ext.BLANK_IMAGE_URL = 'images/s.gif';
Ext.onReady(function(){
Ext.QuickTips.init();

var win;

var locatorForm = new Ext.FormPanel({
        				labelWidth: 140,
        				border:false,
        				width: 500,
						frame:true,
						items: {
            				xtype:'tabpanel',
            				activeTab: 0,
							defaults:{autoHeight:true, bodyStyle:'padding:10px'}, 
           					items:[{
			                title:'ATM Locator',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'combo',
			
			                items: [{
			                    fieldLabel: '<b>State</b>',
			                    id: 'astate'
			                },{
			                    fieldLabel: '<b>City/District/Village</b>',
			                    id: 'acity'
			                    }]
			            },{
			                title:'Branch Locator',
			                layout:'form',
			                defaults: {autoHeight: true},
			                defaultType: 'combo',
			
			                items: [{
			                    fieldLabel: '<b>State</b>',
			                    id: 'bstate'
			                },{
			                    fieldLabel: '<b>City/District/Village</b>',
			                    id: 'bcity'
			                },{
			                    fieldLabel: '<b>Area (Optional)</b>',
			                    id: 'barea'
			                }]
			            }]
			        },
			
			        buttons: [{
			            text: 'Go'
			        },{
			            text: 'Reset'
			        },{
                    text     : 'Close',
                    handler  : function(){
                        win.hide();
                    }
					}]
			    }) 

		
    

function ATM(){
		
        // create the window on the first click and reuse on subsequent clicks
       if(!win){
            win = new Ext.Window({
                applyTo     : 'Locator',
				title		: 'Find Us',
                layout      : 'fit',
                width       : 500,
                height      : 220,
                closeAction :'hide',
                plain       : true,
                items:     locatorForm           
            });
        }
        win.show('locator');
    }
    


var services = new Ext.FormPanel({
        labelWidth: 75, // label settings here cascade unless overridden
        frame:true,
        title: 'Services',
        bodyStyle:'padding:5px 5px 0',
        width: 160,
        defaults: {width:160},
        defaultType: 'label',

        items: [{
			html: '<br/>'
		},new Ext.Button({
        		text: '<b>Express Remit</b>',
				handler: function() {
				window.open("http://www.google.co.in/search?q=Express Remit");
			}
        }),{
			html: '<br/>'
		},
		new Ext.Button({
        		text: '<b>Verified by VISA</b>',
				handler: function() {
				window.open("http://www.google.co.in/search?q=Verified by VISA");
			}
        }),{
			html: '<br/>'
		},new Ext.Button({
        		text: '<b>ATM/Branch Locator</b>',
				tooltip: 
				{
					text:'Find Us in your City/District/Village',
					title: 'ATM/Branch Locator'
				},
				handler: function(){
					ATM();
				}
        }),{
			html: '<br/>'
		},new Ext.Button({
        		text: '<b>Foreign Travel Card</b>',
				handler: function() {
				window.open("http://www.google.co.in/search?q=Foreign Travel Card");
			}
        }),{
			html: '<br/><br/>'
		}
        ]

       
    });
services.render('servicesArea');

var bankingRadio = new Ext.form.RadioGroup({
			fieldLabel:'',
			labelSeparator:'',
			items: [
                    	{
                    		boxLabel: 'Personal',
                    		name: 'choice',
                    		checked: true,
                    		inputValue: 1
                    	},
                    	{
                    		boxLabel: 'Corporate',
                    		name: 'choice',
                    		inputValue: 2
                    	}
                    ]
});


var loginArea = new Ext.FormPanel({
        labelWidth: 75,
        border:false,
        width: 300,
		frame:true,
		xtype: 'fieldset',
		title: 'Login Area',
		defaultType: 'textfield',
        items: [
		{
                    fieldLabel: '<b>Username</b>',
                    id: 'puserName',
                    name: 'puser',
                    allowBlank:false,
                    onFocus: function(){
                    	getFocus(this.id);
                    }
                },{
                    fieldLabel: '<b>Password</b>',
					inputType: 'password',
                    id: 'ppassword',
                    name: 'ppass',
                    allowBlank:false,
                    onFocus: function(){
                    	getFocus(this.id);
                    }
                    },bankingRadio,{
                	xtype: 'checkbox',
                	boxLabel: '<b>Enable Virtual Keyboard</b>',
                	id: 'pkeyChoice',
                	fieldLabel: '',
					labelSeparator: '',
					handler: function(){
            			constructKeyboard('pkeyChoice');
        				}

                },{
                	xtype: 'label',
                	html: '<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">Forgot Password/Trouble Logging In ?</a>'
                }
				],
		waitMsgTarget: true,
        buttons: [{
            text: 'Login',
            handler: function(){
					
						{
							var usr = document.getElementById('puserName').value;
							var pwd = document.getElementById('ppassword').value;
							if(usr == '')
								Ext.MessageBox.alert('Validation Error','Username cannot be left Blank');
							else if(pwd == '')
								Ext.MessageBox.alert('Validation Error','Password cannot be left Blank');
							else if(usr.length < 6)
								Ext.MessageBox.alert('Validation Error','Username should be at least of 6 characters');
							else
							{
										loginArea.getForm().submit({
										method: 'POST',
										waitMsg: 'Verifying Username & Password...',
										url: 'PersonalLoginValidation',
										success: function(){
											window.location = "Home";
										},
										failure: function(response,request){
											var responseData = Ext.decode(request.response.responseText);
											//responseData is blockcount - 1. Keep in mind
											if(responseData.data >= 2)
												Ext.MessageBox.alert('Account Blocked','Many Invalid Logon Attempts have occurred on this account. Hence, your account has been blocked. Contact Administrator to Unblock your Account');
											else
												Ext.MessageBox.alert('Logon Failure','The system could not log you in. Make sure your Username & Password are correct. WARNING: More than 3 invalid logon attempts may lead to blocking of your account!');
										}
									});
								
								//if required Admin case can be added here
							}
						}					
			}
        },{
            text: 'Reset',
            handler: function(){
            		loginArea.getForm().reset();
            }
        }]
    });

    loginArea.render('loginArea');



var mainToolBar = new Ext.Toolbar();
mainToolBar.height = 25;
mainToolBar.width = 956;
mainToolBar.render('mainToolBar');
mainToolBar.add({	
            text:'Home',
			id: 'menuItem1',
			tooltip: 
				{
					text:'Homepage of Website',
					title: 'About Us'
				},
			handler: function() {
				window.location("index.jsp");
			}
},'-',{	
            text:'Product & Services',
			id: 'menuItem2',
			tooltip: 
				{
					text:'An overview of Product & Services',
					title: 'Product & Services'
				}
},'-',{	
            text:'Downloads',
			id: 'menuItem3',
			tooltip: 
				{
					text:'Save relevant documents to your hard disk',
					title: 'Downloads'
				}
},'-',{	
            text:'Contact Us',
			id: 'menuItem4',
			tooltip: 
				{
					text:'Always at your service',
					title: 'Contact Us'
				}
},'-',new Ext.form.Label({
			id:'newsLabel',
			html:'<marquee><b>*** NEWS UPDATES ***   COMING SOON ! KEEP WATCHING !</b></marquee>',
			width: 600			
}),'-',new Ext.form.TextField({
			id:'Search',
			width: 160,
			value:'Search'
}),new Ext.Toolbar.Spacer(),{	
            text:'Search',
			id: 'searchButton',
			pressed: true,
			tooltip: 
				{
					text:'Click to search your query',
					title: 'Search'
				},
			handler: function() {
				var query = document.getElementById('Search').value;
				window.open("http://www.google.co.in/search?q="+query);
			}
}


);
});

