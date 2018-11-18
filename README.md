# stdf 

stdf is a java library for simple java textual data format like 
xml. With it, you can store data with the tag-value 
system into simple plain/text files with a .sdb extension.

The file is split into 3 different types of text:

The species, the tags and the values. 

**Example:**

```bash
  {species}

    (tag)
      some values here
    (*tag)

  {*species}
```

With stdf you can simply create those and add
or read them. You can also convert the values into
Integer, String, double..., but also Lists with
them in it. 

