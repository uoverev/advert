$(document).ready(function() {
    // The maximum number of options
    var MAX_OPTIONS = 5;

    $('#surveyForm')
        .bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                question: {
                    validators: {
                        notEmpty: {
                            message: 'The question required and cannot be empty'
                        }
                    }
                },
                'imgSrc': {
                    validators: {
                        notEmpty: {
                            message: '图片地址不能为空！'
                        },
                        stringLength: {
                            max: 255,
                            message: 'URL不能超过255个字节'
                        }
                    }
                }
            }
        })

        // Add button click handler
        .on('click', '.addButton', function() {
            var $template = $('#optionTemplate'),
                $clone    = $template
                                .clone()
                                .removeClass('hide')
                                .removeAttr('id')
                                .insertBefore($template),
                $imgSrc   = $clone.find('[name="imgSrc[]"]');
            
            // Add new field
            $('#surveyForm').bootstrapValidator('addField', $imgSrc);
        })

        // Remove button click handler
        .on('click', '.removeButton', function() {
            var $row    = $(this).parents('.form-group'),
                $imgSrc = $row.find('[name="imgSrc[]"]');

            // Remove element containing the option
            $row.remove();

            // Remove field
            $('#surveyForm').bootstrapValidator('removeField', $imgSrc);
        })

        // Called after adding new field
        .on('added.field.bv', function(e, data) {
            // data.field   --> The field name
            // data.element --> The new field element
            // data.options --> The new field options

            if (data.field === 'imgSrc[]') {
                if ($('#surveyForm').find(':visible[name="imgSrc[]"]').length >= MAX_OPTIONS) {
                    $('#surveyForm').find('.addButton').attr('disabled', 'disabled');
                }
            }
        })

        // Called after removing the field
        .on('removed.field.bv', function(e, data) {
           if (data.field === 'imgSrc[]') {
                if ($('#surveyForm').find(':visible[name="imgSrc[]"]').length < MAX_OPTIONS) {
                    $('#surveyForm').find('.addButton').removeAttr('disabled');
                }
            }
        });
});