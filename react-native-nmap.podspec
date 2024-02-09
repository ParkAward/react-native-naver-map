require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|
  s.name         = "react-native-nmap"
  s.version      = package['version']
  s.summary      = "React Native Naver Mapview component for iOS + Android"

  s.authors      = { "author" => "parksangjun1363@gmail.com" }
  s.homepage     = "https://github.com/ParkAward/react-native-naver-map"
  s.license      = package['license']
  s.platform     = :ios, "9.0"

  s.source       = { :git => "https://github.com/ParkAward/react-native-naver-map.git", :tag => "#{s.version}"  }
  s.source_files = "ios/**/*.{h,m}"

  s.static_framework = true
  s.dependency 'React'
  s.dependency 'NMapsMap', '~> 3.16.1'

end
