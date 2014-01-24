var customComboboxOnChange = new Array();

(function( $ ) {
    $.widget( "custom.combobox", {
      _create: function() {
        this.wrapper = $( "<span>" )
          .addClass( "custom-combobox" )
          .insertAfter( this.element );
 
        this.element.hide();
        this._createAutocomplete();
        this._createShowAllButton();
      },
      
      autocomplete: function(value){
    	  this.element.val(value);
    	  if(value!=''){
    		  var text = $("#"+this.element.attr('id')+" option:selected").text();
    		  this.input.val(text);
    	  }else{
    		  this.input.val(value);  
    	  }
      },
      
      _createAutocomplete: function() {
        var selected = this.element.children( ":selected" ),
          value = selected.val() ? selected.text() : "";
 
        this.input = $( "<input>" )
          .appendTo( this.wrapper )
          .val( value )
          .attr( "id", this.element.attr("id")+"_input")
          .attr( "title", "" )
          .addClass( "custom-combobox-input ui-widget ui-widget-content ui-state-default ui-corner-left" )
          .autocomplete({
            delay: 0,
            minLength: 0,
            source: $.proxy( this, "_source" ),
            change: function( event, ui ){
              if($('#hasChange').length>0){
            	  $('#hasChange').val('true');
              }	
          	  var eleId = this.id.substr(0, this.id.length-6);
          	  var mtd = customComboboxOnChange[eleId];
          	  if(mtd!=''){
          		eval(mtd);  
          	  }          	  
            }
          })
          .tooltip({
            tooltipClass: "ui-state-highlight"
          });
 
        this._on( this.input, {
          autocompleteselect: function( event, ui ) {
            ui.item.option.selected = true;
            this._trigger( "select", event, {
              item: ui.item.option
            });
          },          
          autocompletechange: "_removeIfInvalid",
         
        });
      },
 
      _createShowAllButton: function() {
        var input = this.input,
          wasOpen = false;
 
        $( "<a>" )
          .attr( "tabIndex", -1 )

          .appendTo( this.wrapper )
          .button({
            icons: {
              primary: "ui-icon-triangle-1-s"
            },
            text: false
          })
          .removeClass( "ui-corner-all" )
          .addClass( "custom-combobox-toggle ui-corner-right" )
          .mousedown(function() {
            wasOpen = input.autocomplete( "widget" ).is( ":visible" );
          })
          .click(function() {
            input.focus();
 
            // Close if already visible
            if ( wasOpen ) {
              return;
            }
 
            // Pass empty string as value to search for, displaying all results
            input.autocomplete( "search", "" );
          });
      },
 
      _source: function( request, response ) {
        var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
        response( this.element.children( "option" ).map(function() {
          var text = $( this ).text();
          if ( this.value && ( !request.term || matcher.test(text) ) )
            return {
              label: text,
              value: text,
              option: this
            };
        }) );
      },
 
      _removeIfInvalid: function( event, ui ) {
 
        // Selected an item, nothing to do
        if ( ui.item ) {
          return;
        }
 
        // Search for a match (case-insensitive)
        var value = this.input.val(),
          valueLowerCase = value.toLowerCase(),
          valid = false;
        this.element.children( "option" ).each(function() {
          if ( $( this ).text().toLowerCase() === valueLowerCase ) {
            this.selected = valid = true;
            return false;
          }
        });
 
        // Found a match, nothing to do
        if ( valid ) {
          return;
        }
 
        // Remove invalid value
        this.input.val( "" );

        this.element.val( "" );
        this._delay(function() {
          this.input.tooltip( "close" ).attr( "title", "" );
        }, 2500 );
        this.input.data( "ui-autocomplete" ).term = "";
      },
 
      _destroy: function() {
        this.wrapper.remove();
        this.element.show();
      }
    });
  })( jQuery );


function updateOptionList(paraList, optionListId){	
	var paraMap = {};
	if(paraList instanceof Array){
		for(var i=0; i<paraList.length; i++){
			paraMap[paraList[i]] = $("#"+paraList[i]).val();		
		}
	}else{
		paraMap[paraList] = $("#"+paraList).val();
	}
	
	OptionListRemote.getOptionList(paraMap, optionListId, {
		callback: function(obj){
			$("#" + optionListId).combobox('autocomplete',"");
			$('#'+ optionListId +' option:gt(0)').remove();// remove old options
			if(obj!=null){				
				var $el = $("#"+optionListId);
				for(var i=0; i<obj.length; i++){
					var testObj = obj[i];
				  $el.append($("<option></option>")
				     .attr("value", testObj.code).text(testObj.codeWithDesc));
				};
			}
		}
	});
};