# csv-generator
I used this while researching csv parsing for ers improvements. There may be some jank left in there but it generates files soooooo

It's currently configured to generate an EMI40_RCL_V3 csv file 1000 rows long with errors every 4 rows. 
You can use `sbt run` and it will generate the file.

If you want to change the data and filename that can all be done by changing what's passed into the `generateInvalidFile` function at the bottom of `Main.scala`. Valid data for each scheme can be found in the acceptance tests repository

Drop me a message if it doesn't work/make sense
