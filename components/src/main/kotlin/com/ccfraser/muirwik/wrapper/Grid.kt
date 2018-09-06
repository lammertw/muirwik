package com.ccfraser.muirwik.wrapper

import react.RBuilder
import react.RComponent
import react.RHandler
import react.RState
import styled.StyledHandler
import styled.StyledProps


@JsModule("@material-ui/core/Grid")
private external val gridDefault: dynamic

@Suppress("UnsafeCastFromDynamic")
private val gridComponent: RComponent<MGridProps, RState> = gridDefault.default

enum class MGridAlignContent {
    Stretch, Center, FlexStart, FlexEnd, SpaceBetween, SpaceAround;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

enum class MGridAlignItems {
    Stretch, Center, FlexStart, FlexEnd, Baseline;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

enum class MGridDirection {
    Row, RowReverse, Column, ColumnReverse;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

enum class MGridJustify {
    FlexStart, Center, FlexEnd, SpaceBetween, SpaceAround;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

enum class MGridSize(internal val sizeVal: Any) {
    False(false), Auto("auto"), True(true), Cells1(1), Cells2(2),
    Cells3(3), Cells4(4), Cells5(5), Cells6(6), Cells7(7),
    Cells8(8), Cells9(9), Cells10(10), Cells11(11), Cells12(12);
}

enum class MGridWrap {
    NoWrap, Wrap, WrapReverse;

    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}

enum class MGridSpacing(val size: Int) {
    Spacing0(0), Spacing8(8), Spacing16(16), Spacing24(24), Spacing40(40);
}

interface MGridProps : StyledProps {
    var alignContent: String
    var alignItems: String
    var container: Boolean
    var item: Boolean
    var lg: Any
    var md: Any
    var sm: Any
    var spacing: Int
    var xl: Any
    var xs: Any
    var wrap: String
    var zeroMinWidth: Boolean
}

/**
 * The material design components allows a grid item to be a container and an item. We have simplified things here
 * since different properties apply depending on if it is a container or an item. So, if you want both, you will have
 * to add an extra child item.
 */
fun RBuilder.mGridContainer(
        spacing: MGridSpacing = MGridSpacing.Spacing16,
        alignContent: MGridAlignContent = MGridAlignContent.Stretch,
        alignItems: MGridAlignItems = MGridAlignItems.Stretch,
        wrap: MGridWrap = MGridWrap.Wrap,

        className: String? = null,
        handler: StyledHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.alignContent = alignContent.toString()
    attrs.alignItems = alignItems.toString()
    attrs.container = true
    attrs.spacing = spacing.size
    attrs.wrap = wrap.toString()

    setStyledPropsAndRunHandler(className, handler)
}

fun RBuilder.mGridItem(
        xs: MGridSize = MGridSize.Auto,
        sm: MGridSize = MGridSize.Auto,
        md: MGridSize = MGridSize.Auto,
        lg: MGridSize = MGridSize.Auto,
        xl: MGridSize = MGridSize.Auto,
        zeroMinWidth: Boolean? = null,

        className: String? = null,
        handler: RHandler<MGridProps>? = null) = createStyled(gridComponent) {
    attrs.item = true
    attrs.sm = sm.sizeVal
    attrs.md = md.sizeVal
    attrs.lg = lg.sizeVal
    attrs.xs = xs.sizeVal
    attrs.xl = xl.sizeVal
    zeroMinWidth?.let { attrs.zeroMinWidth = it }

    setStyledPropsAndRunHandler(className, handler)
}
