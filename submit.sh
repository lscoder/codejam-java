mkdir submit > /dev/null 2>&1
rm submit/*.out > /dev/null 2>&1
cp src/br/com/lscoder/Main.java submit/

# Copying the latest output file created
ls -t files/output/*.out | head -n 1 | xargs -I % cp % ./submit/