// ----------------------------------------------------------------------------
// crossSelect - A jQuery Plugin to make multiple select boxes more intuitive
// v 0.5, requires jQuery 1.3.2 or later (may work with jQuery 1.3, but untested)
//
// Dual licensed under the MIT and GPL licenses.
// ----------------------------------------------------------------------------
// Copyright (C) 2009 Rhys Evans
// http://wheresrhys.co.uk/resources/
// ----------------------------------------------------------------------------
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.
// ----------------------------------------------------------------------------
// DOCUMENTATION
// To apply the plug-in to a <select> tag/some <select> tags add the line, eg 
//
//           $('select[multiple="multiple"]').crossSelect(); 
//  
// The plugin takes 5 optional parameters in JSON form:
// rows (integer, default 8) - the height in rows of new select box
// font (integer, default 12) - font-size in pixels
// listWidth - (integer, default 150) - minimum width of the select box
// vertical (string, default 'scroll') - 'scroll' adds a scroll bar if the number of options is greater than the rows parameter
//                                       'expand' ignores the rows parameter and expands to show all rows
// horizontal (string, default 'hide') - 'scroll' adds a scroll bar if the width of any items is wider than listWidth
//                                       'expand' expands the width to the width of the longest item
//                                       'hide' clips the horizontal display if items are longer than the listWidth
// dblclick (boolean, default true) - true enables quick selection of items with a double click
//									  false disables double clicks	
// clickSelects (boolean, default false) - true enables quick selection of items using a single click
//										- false disables single click selection
// clickAccumulate (boolean, default false) - true: a click on a new option will not unfocus previously focused ones
//												false: clicking on a new item unfocuses all previously focused items
// 
//  select_txt, remove_txt, selectAll_txt, removeAll_txt (string) - set the text to appear on the buttons
//  eg $('select').crossSelect({horizontal:'expand', font: '17', rows: '20'});
//  NEW IN VERSION 0.5 
//  - Can select many at one time using the clicks accumulate setting
//  - Multilingual - text on buttons can be set


// ----------------------------------------------------------------------------
(function($) { 
	$.fn.crossSelect = function(options) {
		var pars = $.extend({}, jQuery.fn.crossSelect.defaults, options);
		var select, context, longestOpt, dimensions = new Array(), optionsList, chosenList, buttons;
		var temp_focus="";
		return this.each(function() {
			
			if($(this).attr('multiple') == 'multiple')
			{	
				select = $(this).hide().wrap('<div class="jqxs"></div>');
				context = $(select).parent();
				getLongestOpt();
				setDimensions();
				populateLists();
				createCrossSelecter();
				addListeners();
				
				// enable removeAll Button if choseList.size > 0
				if ($('li', $('.jqxs_chosenList', context)).size() > 0)	{
					enable($('.jqxs_removeAllButton', context));
				}
			}
		});
		
		function createCrossSelecter() {		
			buttons = '<dl><dd class="padding"><div class="jqxs_buttons">';
			if(!pars.clickSelects)
			{
				buttons+= '<input type="button" value="'+pars.select_txt+'" class="jqxs_selectButton" disabled="disabled"></input><input type="button" value="'+pars.remove_txt+'" class="jqxs_removeButton" disabled="disabled"></input>';
			}
			buttons +='<input type="button" value="'+pars.selectAll_txt+'" class="jqxs_selectAllButton"';
			if ($('option:not([selected=true])',select).size() === 0)
			{
				buttons+=' disabled="disabled"';
			}		
			buttons+='></input><input type="button" value="'+pars.removeAll_txt+'" class="jqxs_removeAllButton"';
			var test = $('option',select);			
			if ($('option[selected=true]',select).size() === 0)
			{
				buttons+=' disabled="disabled"';
			}		
			buttons+='></input></div></dd></dl>';
			$(context).append(optionsList + buttons + chosenList +'<div style="clear:left;"></div>');
			$('ul', context).css({height: dimensions['height'],width:dimensions['width']});
			if (pars.horizontal === 'hide')
			{
				$('ul', context).css({'overflow-x': 'hidden'});
			}

		}
		
		function setDimensions() {	
			switch (pars.vertical)
			{
				case 'expand':
					dimensions['height'] = pars.rows * (pars.font *1.25);
					break;
				case 'scroll':
					dimensions['height'] = pars.rows * (pars.font *1.25);
					break;
			}

			switch (pars.horizontal)
			{

				case 'expand':
					dimensions['width'] = Math.max(longestOpt + 10, pars.listWidth);
					break;
				case 'scroll':
					dimensions['width'] = pars.listWidth;
					if(longestOpt > pars.listWidth){dimensions['height'] = dimensions['height'] + 24;}
					break;
				case 'hide':
					dimensions['width'] = pars.listWidth; // remember to set overflow-x hidden
					break;
			}
		}
			
		function populateLists() {
			var optOrder = 0;
			optionsList ='<dl class="border"><dt>Available List</dt><dd><ul class="jqxs_optionsList">';
			chosenList='<dl class="border"><dt>Selected List</dt><dd><ul class="jqxs_chosenList">';
			var newItem;
			
			$(select).children('option').each(function() {		
				newItem = ''; 
				($(this).attr('selected') === 'selected')? newItem += '<li class="jqxs_selected"' : newItem+='<li';
				newItem += ' style="line-height: '+(pars.font *1.25)+'px; height:'+pars.font *1.25+'px;font-size: '+pars.font+'px;width:'+longestOpt+'px">';
				newItem+= $(this).text() + '<span>' + optOrder +'</span></li>';
				optionsList += newItem;
				if($(this).attr('selected') === 'selected')
				{
					chosenList += newItem;
				}
				
				
				optOrder++;
			});
			chosenList +='</ul></dd></dl>';
			optionsList +='</ul></dd></dl>';
		}
		
		function getLongestOpt() {
				var text = "";
				
				$('option',select).each(function() {
					text+= $(this).text() +"<br />";
				});
				
				
				$(context).append('<span class="jqxs_ruler" style="font-size:'+pars.font+'px">'+text+'</span>');
				longestOpt = $('span', context).width();
				$('span', context).remove();
				
				// if longestOpt == 0 --> cannot append span to $(context)
				// try to append span to form in order to get the max width of the options
				if (longestOpt == 0)	{
					if ($(document.forms[0]) != undefined)	{
						$(document.forms[0]).append('<span id="jqxs_ruler" class="jqxs_ruler" style="font-size:'+pars.font+'px">'+text+'</span>');
					    longestOpt = $('#jqxs_ruler',document.forms[0]).width();
						$('#jqxs_ruler', document.forms[0]).remove();
					}
				}


		}
		
		function addListeners() {
			var thisSelect = select;
			var thisOptions = $('.jqxs_optionsList', context);
			var thisChosen = $('.jqxs_chosenList', context);
			var thisSelBut = $('.jqxs_selectButton', context);
			var thisRemBut = $('.jqxs_removeButton', context);
			var thisSelAllBut = $('.jqxs_selectAllButton', context);
			var thisRemAllBut = $('.jqxs_removeAllButton', context);
			if(pars.clickSelects)
			{
				$('li', thisOptions).click(selectNow);
				$('li', thisChosen).click(removeNow);
			} else {
				$('li', context).click(itemFocus);
				$(thisSelBut).click(selectMany);
				$(thisRemBut).click(removeMany);
			}
			if(pars.dblclick)
			{
				$('li', thisOptions).dblclick(selectNow);
				$('li', thisChosen).dblclick(removeNow);
			}
			$(thisSelAllBut).click(selectAll);
			$(thisRemAllBut).click(removeAll);
			
			function sorting(element1, element2)  
            {  
                 if (element1 < element2){  
                    return -1;  
                 }else if(element1==element2){  
                    return 0;  
                 }else {  
                    return 1;  
                 }  
            } 
			
			function itemFocus() {
				if($(this).hasClass('jqxs_focused'))
				{
					temp_focus = '';
					$(this).removeClass('jqxs_focused');				
					if($(this).parent().children('.jqxs_focused').length === 0)
					{
						disable(thisSelBut);
						disable(thisRemBut);
					}
					return;
				}
				if(!pars.clicksAccumulate)
				{
					$('li',thisOptions).removeClass('jqxs_focused');
					$('li',thisChosen).removeClass('jqxs_focused');
				} else {
					if($(this).parent().hasClass('jqxs_optionsList'))
					{
						$('li',thisChosen).removeClass('jqxs_focused');
					} else {
						$('li',thisOptions).removeClass('jqxs_focused');
					}
				}
				
				$(this).addClass('jqxs_focused');
				
				//for shift select
				//---->>>>
				if(temp_focus == ''){
					temp_focus += $('span',this).text() + ";";
				}
				
				if(window.event.shiftKey) {
					temp_focus += $('span',this).text() + ";";
					var temp_v = new Array();
					var temp_w = new Array();
					var temp_x = new Array();
					if(temp_focus != ''){
						
						temp_v = temp_focus.split(";"); 
						
						for(var i = 0 ; i < temp_v.length ; i++){
							if(isNaN(parseInt(temp_v[i]))) continue;
							temp_w.push(parseInt(temp_v[i]));
						}
						
						temp_x = temp_w.sort(sorting);
						
						for(var i = temp_x[0] ; i <= temp_x[temp_x.length -1] ; i++){
							if ($(this).parent().hasClass('jqxs_optionsList')){
								if($($(thisSelect).children('option')[i]).attr('selected') != 'selected'){
									$($('li', thisOptions)[i]).addClass('jqxs_focused');
								}
								
								/*$('li', $('.jqxs_optionsList', context)).each(function() {
									var position = $('span',this).text();
									var option = $(thisSelect).children('option')[position];
									if(position == i && $(option).attr('selected') != 'selected'){
										$(this).addClass('jqxs_focused');
									}
								});*/
							} else if ($(this).parent().hasClass('jqxs_chosenList')){
								$($('li', thisChosen)[i]).addClass('jqxs_focused');
							}
						}
					
					}
				} 
				// <<<---- End shift select	
				
				if ($(this).parent().hasClass('jqxs_optionsList')){
					enable(thisSelBut);
					disable(thisRemBut);
				}else{
					enable(thisRemBut);
					disable(thisSelBut);
				}
				
			}
			
			
			function selectOne() {	
				var position = $('span',this).text();
				
				var option = $(thisSelect).children('option')[position];
				
				//if($(option).attr('selected') === false)
				if($(option).attr('selected') === undefined || $(option).attr('selected') === 'selected')
				{
					$(option).attr('selected','selected');
					$(this).removeClass('jqxs_focused');
					if(pars.clickSelects)
					{
						$(this).clone().appendTo(thisChosen).click(removeNow);
					} else {
						if(pars.dblclick) 
						{
							$(this).clone().appendTo(thisChosen).click(itemFocus).dblclick(removeNow);
						} else {
							$(this).clone().appendTo(thisChosen).click(itemFocus);
						}
					}
					$(this).addClass('jqxs_selected');
				}
				
				//for sorting
				//---->>>
				var temp_y = new Array();
				$('li', $('.jqxs_chosenList', context)).each(function() {
					temp_y.push(parseInt($('span',this).text()));
				});
				
				temp_y.sort(sorting);
				
				for(var i=0;i<temp_y.length;i++){
					$('li', $('.jqxs_chosenList', context)).each(function() {
						if(temp_y[i] == $('span',this).text()){
							$(this).remove().appendTo(thisChosen).click(itemFocus).dblclick(removeNow);
							return;
						}
					});
				}
				//<<<----end sorting
			}
			
			function selectAllFunc() {	
				var position = $('span',this).text();
				
				var option = $(thisSelect).children('option')[position];
				
				//if($(option).attr('selected') === false)
				if($(option).attr('selected') === undefined || $(option).attr('selected') === 'selected')
				{
					$(option).attr('selected','selected');
					$(this).removeClass('jqxs_focused');
					if(pars.clickSelects)
					{
						$(this).clone().appendTo(thisChosen).click(removeNow);
					} else {
						if(pars.dblclick) 
						{
							$(this).clone().appendTo(thisChosen).click(itemFocus).dblclick(removeNow);
						} else {
							$(this).clone().appendTo(thisChosen).click(itemFocus);
						}
					}
					$(this).addClass('jqxs_selected');
				}
			}
			
			function removeOne() {
				temp_focus = "";
				var position = $('span',this).text();
				
				var option = $(thisSelect).children('option')[position];
				//if($(option).attr('selected') === true)
				if($(option).attr('selected') === 'selected')
				{
					//$(option).attr('selected','');
					$(option).removeAttr("selected");
					$($(thisOptions).children('li')[position]).removeClass('jqxs_selected');
					$(this).remove();
				}
				
			}
			
			function selectNow() {
				temp_focus = "";
				$(this).each(selectOne);
				adjustButtons('sel');
			}
			
			function removeNow() {
				temp_focus = "";
				$(this).each(removeOne);
				adjustButtons('rem');
			}
			
			function selectMany() {
				temp_focus = "";
				$('.jqxs_focused',thisOptions).each(selectOne);
				adjustButtons('sel');
			}
			
			function removeMany() {
				temp_focus = "";
				$('.jqxs_focused',thisChosen).each(removeOne);
				adjustButtons('rem');
			}
			
			function selectAll(){
				temp_focus = "";
				removeAll();
				//$('li',thisOptions).each(selectOne);
				$('li',thisOptions).each(selectAllFunc);
				disable(thisRemBut);
				adjustButtons('sel');
			}
			
			function removeAll(){
				$('li',thisChosen).each(removeOne);
				disable(thisSelBut);
				adjustButtons('rem');
			}
			function adjustButtons(action) {
				switch(action)
				{
					case 'sel':
						disable(thisSelBut);
						enable(thisRemAllBut);
						if($('li:not(.jqxs_selected)',thisOptions).size() === 0 ) {disable(thisSelAllBut);}
						break;
					case 'rem':
						disable(thisRemBut);
						enable(thisSelAllBut);
						if($('li',thisChosen).size() === 0 ) {disable(thisRemAllBut);}
						break;
				}
			}
		}
		
		function enable(button){
			$(button).removeAttr("disabled");
		}
		
		function disable(button){
			$(button).attr('disabled', 'disabled');
		}	
	};
})(jQuery);
jQuery.fn.crossSelect.defaults = {
	vertical: 'scroll',
	horizontal: 'hide',
	listWidth: 150,
	font: 12,
	rows: 8,
	dblclick: true,
	clickSelects: false,
	clicksAccumulate: false,
	select_txt: 'select',
	remove_txt: 'remove',
	selectAll_txt: 'select all',
	removeAll_txt: 'remove all'
};