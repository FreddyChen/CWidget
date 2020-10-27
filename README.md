# CWidget
一些Android通用的常用控件，比如可支持自定义属性shape/selector/corners radius的CTextButton

使用方式：implementation 'com.freddy:c_widget:1.0.0'
支持属性：
```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <declare-styleable name="CTextButton">
        <attr name="ctb_normalBackgroundColor" format="color|reference" />
        <attr name="ctb_pressedBackgroundColor" format="color|reference" />
        <attr name="ctb_disabledBackgroundColor" format="color|reference"/>
        <attr name="ctb_normalTextColor" format="color|reference" />
        <attr name="ctb_pressedTextColor" format="color|reference" />
        <attr name="ctb_disabledTextColor" format="color|reference" />
        <attr name="ctb_normalStrokeColor" format="color|reference" />
        <attr name="ctb_pressedStrokeColor" format="color|reference" />
        <attr name="ctb_disabledStrokeColor" format="color|reference" />
        <attr name="ctb_strokeWidth" format="dimension|reference" />
        <attr name="ctb_dashWidth" format="dimension|reference" />
        <attr name="ctb_dashGap" format="dimension|reference" />
        <attr name="ctb_cornerRadius" format="dimension|reference" />
        <attr name="ctb_shape">
            <enum name="rectangle" value="0" />
            <enum name="oval" value="1" />
            <enum name="line" value="2" />
            <enum name="ring" value="3" />
        </attr>
    </declare-styleable>

    <declare-styleable name="CImageButton">
        <attr name="cib_normalImageRes" format="color|reference" />
        <attr name="cib_pressedImageRes" format="color|reference" />
        <attr name="cib_disabledImageRes" format="color|reference" />
    </declare-styleable>
</resources>
```

请切换到master分支查看代码
end
