Android ViewBadger
==================

A simple way to "badge" any given Android view at runtime without having to cater for it in layout.

![Demos](http://www.jeffgilfelt.com/viewbadger/badge1.png "Demos")&nbsp;
![ListAdapter](http://www.jeffgilfelt.com/viewbadger/badge2.png "ListAdapter")&nbsp;
![Layout Tests](http://www.jeffgilfelt.com/viewbadger/badge3.png "Layout Tests")

Note: If your aim is to replicate the iOS icon and TabBar badge UI for notifications, consider using Android UI conventions such as the number field of the [Notification](http://developer.android.com/reference/android/app/Notification.html "Notification") class rather than this method.

Usage
-----

Simple example:

    View target = findViewById(R.id.target_view);
    BadgeView badge = new BadgeView(this, target);
    badge.setText("1");
    badge.show();

This project contains a fully working example application. Refer to the `DemoActivity` class for more custom badge examples, including custom backgrounds and animations. `BadgeView` is a subclass of `TextView` so you can use all of `TextView`'s methods to style the appearance of your badge.

Implementation
--------------

To use ViewBadger in your own Android project, simply copy `android-viewbadger.jar` (available from this repository's package downloads) into your project's `/libs` directory and add it to the build path.

Credits
-------

Author: Jeff Gilfelt

The code in this project is licensed under the Apache Software License 2.0.
<br />
Copyright (c) 2011 readyState Software Ltd.