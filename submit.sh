rm submit/*.out
rm submit/*.zip
rm -rf submit/src
cp -rp src/ submit/src

ls -t files/output/*.out | head -n 1 | xargs -I % cp % ./submit/

cd submit

zip -r src.zip src/
rm -rf src

# Exit from 'submit' folder
cd ..
